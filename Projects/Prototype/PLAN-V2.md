# ZenVed v2 — Interactive Upgrade Plan

Branch: `v2-interactive` (forked from `main`). `main` stays live at https://zenved-site.vercel.app/ while we iterate here. Merge to `main` only when approved.

## Problems with v1 (user feedback)

1. **Looks static.** No hover states, no scroll reveals, no click interactions. Reads like a printed brand PDF.
2. **Background is flat black.** Needs depth, movement, visual richness.
3. **Video placement is off.** Poster/overlay cluttered, alignment wrong, play button sits poorly.
4. **Not enough "one page, many moments".** Every section should feel distinct — different animation language.
5. **Not professional-grade** compared to Flipkart, Linear, Stripe, Vercel.

## Design language — v2

**Dark-rich, not flat-black.** Base `#030612`. Fixed-position animated gradient mesh underneath: three slowly-drifting radial orbs (teal, gold, navy) blurred heavily, with a faint 60px dot-grid overlay. Gives the "deep space / frontier tech" feel without reading as empty black.

**Motion grammar:**
- **Entrance** — `opacity 0 → 1, y +24 → 0`, staggered 60ms children. Triggered by `whileInView` (threshold 20%).
- **Hover (cards)** — `y -2, border from 6% → 30% opacity, box-shadow 0 0 40px gold/15%`, 200ms ease.
- **Hover (interactive)** — scale 1.02 max, never bouncy, always premium.
- **Click** — clear state change (accordion expand, tab switch, copy confirmation toast).
- **Reduced-motion** — every animation behind `prefers-reduced-motion` guard.

## Section-by-section interactions

| Section | Primary interaction |
|---|---|
| **Cover (hero)** | Staggered text reveal; mouse-parallax on gradient orbs; phase pills glow on hover; animated down-arrow |
| **Nav** | Scroll progress bar at top; active section indicator slides with IntersectionObserver; "Register" pill glows |
| **Brand Foundation** | Values grid: staggered entrance + icon rotates on hover; Audience cards: click-to-expand for full detail; Tone grid: row slides + colored bar pulses on hover |
| **Logo System** | Logo boxes: each hover = accent-color glow ring; **Video: fixed 16:9 frame with clean play button, logo watermark bottom-right not cluttered top-left, proper aspect ratio, reveal animation on play** |
| **Colour System** | Swatches expand on hover (show HSL/RGB); click hex → copies + toast "Copied #C9A84C" |
| **Typography** | Each row: hover zooms sample text, shows character set preview |
| **Launch Readiness** | LP wireframe: collapsible accordion per block; **animated stats strip counting up** (40–80%, 1M+, ₹17B, 97M); social kit cards tilt on hover |
| **Product Identity** | Thumbnails: hover overlay slide-up with full metadata; Button system: clickable live demo with ripple effect |
| **Certificate** | Mouse-tilt 3D perspective; gold shine sweep on hover; corner brackets animate on reveal |
| **Handoff** | Timeline: scroll-linked progress fill; table rows: hover highlight + format tags pulse |
| **Register QR** | QR pulse ring animation; countdown timer (May 20 launch); magnetic hover on CTA buttons; WhatsApp ripple |

## New components to create

- `components/Background.tsx` — fixed animated gradient mesh + dot grid
- `components/ScrollProgress.tsx` — top progress bar
- `components/ui/AnimatedCounter.tsx` — counts up on view (framer-motion + `useMotionValue`)
- `components/ui/Reveal.tsx` — generic scroll-triggered fade/slide wrapper
- `components/ui/Accordion.tsx` — expand/collapse primitive
- `components/ui/CopyHex.tsx` — click-to-copy swatch with toast
- `components/ui/Toast.tsx` — minimal toast (for copy confirmations)
- `components/ui/MagneticButton.tsx` — button with magnetic cursor pull
- `components/ui/Tilt.tsx` — 3D mouse-tilt wrapper (certificate)
- `components/Countdown.tsx` — registration countdown

## Components to rewrite

**All existing section components get rewritten** to use the new motion grammar. Structure stays, visuals change.

Priority order (if time runs short):
1. `Background.tsx` + `globals.css` (foundation — everything sits on this)
2. `LogoVideo.tsx` (concrete fix requested by user)
3. `Cover.tsx` (first impression)
4. `BrandFoundation.tsx` (most content, most impact)
5. `LaunchReadiness.tsx` (stats counters, biggest "wow")
6. Rest in order

## What stays from v1

- Content (every word from prototype).
- Design tokens (navy / gold / teal palette).
- Three Google fonts (Cormorant / Rajdhani / JetBrains Mono).
- Next 16 + Tailwind v4 + TypeScript stack.
- `force-static`, Vercel config, SEO files.
- Brand assets in `public/brand/`.

## Performance guardrails

- Framer Motion via `m` import + `LazyMotion` — tree-shakes to ~12 KB.
- Client components only for animated leaves; pages stay server.
- Video remains `preload="none"`.
- Scroll listeners throttled via `requestAnimationFrame`.
- `prefers-reduced-motion` kills all non-essential motion.

Lighthouse target unchanged: LCP < 1.5s, CLS < 0.05, JS < 100 KB gzipped (bumped from 80 because of motion lib).

## Deploy strategy

- `main` → always points at last-approved production (current Vercel live).
- `v2-interactive` → work happens here.
- When approved: `git checkout main && git merge v2-interactive && git push` → Vercel auto-deploys.
- If v2 breaks on prod: `git revert` or push `main` back to `159f358`.

## Order of execution

1. Background + globals refresh ← **start here**
2. Reveal + motion primitives (Reveal, AnimatedCounter, Accordion, MagneticButton, Tilt, Toast, CopyHex)
3. Fix LogoVideo (alignment, play button, watermark)
4. Rewrite Cover
5. Scroll progress + Nav upgrade
6. Rewrite Brand Foundation, Logo System, Colour, Typography
7. Rewrite Launch Readiness (stats counters)
8. Rewrite Product Identity, Certificate, Handoff, Register
9. Countdown timer
10. Commit to `v2-interactive`, push branch, Vercel creates preview URL
11. User reviews preview URL → merge to `main` if approved
