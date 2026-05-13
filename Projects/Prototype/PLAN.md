# ZenVed Site — Build Plan

Source prototype: `New Text Document.html.txt` (single-file HTML, ~1600 lines, "ZenVed Brand Guidelines" page).

## 0. Ground rules (locked decisions)

- **Stack:** Next.js 15 (App Router) + TypeScript + Tailwind CSS v4.
- **Runtime role of Node.js:** build/dev tooling only. No API routes, no server data fetching, no server actions.
- **Output:** fully static. Every route uses `export const dynamic = 'force-static'`. Target is static HTML/CSS/JS deployed to Vercel's CDN edge.
- **Deploy target:** Vercel (zero-config, auto preview deploys from git).
- **Location:** `C:\Users\ankur\Projects\Prototype\zenved-site` (subfolder — leave prototype HTML untouched).
- **Package manager:** npm.
- **Node version:** pin via `.nvmrc` / `engines` to Node 20 LTS.

## 1. Scaffold command

```
npx create-next-app@latest zenved-site --ts --tailwind --app --no-src-dir --eslint --import-alias "@/*" --use-npm --turbopack --yes
```

Flags rationale: TypeScript, Tailwind, App Router, no `src/` (simpler for a marketing site), ESLint on, `@/*` alias, npm, Turbopack dev, skip prompts.

## 2. Design tokens (port from prototype CSS `:root`)

Move to Tailwind v4 `@theme` block in `app/globals.css`:

| Token      | Value                     | Purpose                  |
|------------|---------------------------|--------------------------|
| `--navy`   | `#05080F`                 | page background          |
| `--navy2`  | `#090C15`                 | section bg alt           |
| `--navy3`  | `#0D1020`                 | card bg                  |
| `--gold`   | `#C9A84C`                 | primary accent / headings|
| `--gold2`  | `#E8C96A`                 | hover / highlight        |
| `--gold3`  | `#8B6A20`                 | scrollbar / muted gold   |
| `--teal`   | `#1E9E8E`                 | secondary accent         |
| `--teal2`  | `#25C4B2`                 | eyebrow labels           |
| `--cyan`   | `#00BCD4`                 | tertiary                 |
| `--white`  | `#F2EDE6`                 | body text (warm off-white)|
| `--grey`   | `#8A8478`                 | muted text               |
| `--border` | `rgba(201,168,76,0.12)`   | hairlines                |
| `--card`   | `rgba(255,255,255,0.03)`  | glass cards              |

Fonts (three families used in prototype):
- **Cormorant Garamond** — display/serif headings. Weights: 300, 400, 600, 700 + italic 300/400.
- **Rajdhani** — body sans. Weights: 400, 500, 600, 700.
- **JetBrains Mono** — eyebrow/mono labels. Weights: 300, 400, 500.

## 3. Performance levers (must-apply, in order)

1. **Self-host fonts via `next/font/google`** — no `<link>` to fonts.googleapis.com. Subset to `latin`. Only load italic weights actually referenced. Assign CSS variables (`--font-serif`, `--font-sans`, `--font-mono`) and wire into Tailwind theme.
2. **Static rendering** — add `export const dynamic = 'force-static'` + `export const revalidate = false` to every route. Confirm build output shows `○ (Static)` for all pages.
3. **Pre-blurred orbs** — prototype uses `filter: blur(80px)` on 500–600px divs (expensive on mobile GPUs). Replace with a single pre-blurred WebP/AVIF positioned absolutely. Export from a one-off script or Figma at ~2x DPR and let `next/image` handle responsive sizing.
4. **`next/image` everywhere** for raster assets (AVIF+WebP, lazy by default, explicit `width`/`height` to prevent CLS).
5. **Component splitting** — one section per file so unused sections are tree-shaken if routes diverge later. Keep client components minimal; most sections are pure RSC (zero JS shipped).
6. **`'use client'` only where needed** — scroll animations, intersection observers, Lenis, GSAP. Never for static markup.
7. **Code-split heavy libs** — `dynamic(() => import('...'), { ssr: false })` for GSAP/Lenis so they don't block initial HTML.
8. **Reduced motion** — wrap every animation in `matchMedia('(prefers-reduced-motion: reduce)')` guard.
9. **Lighthouse budget (CI-enforced later):** LCP < 1.5s, CLS < 0.05, INP < 200ms, total JS < 80 KB gzipped on home route.
10. **`next.config.ts`**: enable `compress`, `reactStrictMode`, `images.formats = ['image/avif','image/webp']`, `experimental.optimizePackageImports` for `framer-motion` and `lucide-react`.

## 4. Component breakdown (from prototype sections)

Read the prototype top-to-bottom and split into:

- `components/Cover.tsx` — hero with grid overlay, orbs, lotus, title, sub.
- `components/Nav.tsx` — sticky nav (if present in prototype).
- `components/Philosophy.tsx`
- `components/LogoSystem.tsx`
- `components/Typography.tsx`
- `components/ColorPalette.tsx`
- `components/Iconography.tsx`
- `components/Voice.tsx`
- `components/Applications.tsx`
- `components/Footer.tsx`

(Exact list to be finalized after full prototype read — I will read the file in chunks and adjust this list before coding.)

Shared primitives:
- `components/ui/SectionHeading.tsx`
- `components/ui/Eyebrow.tsx`
- `components/ui/Card.tsx`
- `components/ui/GridOverlay.tsx`
- `components/ui/Orbs.tsx`

## 5. Dependencies (beyond create-next-app defaults)

```
framer-motion        # hero/accent animation only
lenis                # smooth scroll
gsap                 # ScrollTrigger reveals (dynamic import)
clsx                 # className joining
lucide-react         # icons (tree-shakeable)
```

No UI kit runtime (shadcn is copy-paste, added per-component as needed). No state library. No data-fetching library.

## 6. File/folder layout

```
zenved-site/
  app/
    layout.tsx          # fonts, <html lang="en">, metadata, theme vars
    page.tsx            # single long-scroll page composing all sections
    globals.css         # @theme tokens, base resets, scrollbar
    opengraph-image.tsx # auto OG image
  components/
    ...sections...
    ui/...primitives...
  lib/
    motion.ts           # reduced-motion helpers
  public/
    orbs/orb-gold.webp
    orbs/orb-teal.webp
  next.config.ts
  tailwind.config.ts    # (only if needed; v4 prefers CSS-first)
  .nvmrc                # 20
  README.md
```

## 7. Accessibility & SEO baseline

- `<html lang="en">`, proper heading hierarchy (`h1` once, then `h2` per section).
- Color contrast check: gold `#C9A84C` on navy `#05080F` = ~8.5:1 ✅. Grey `#8A8478` on navy = ~4.9:1 ✅ for body, borderline for small text — bump to `#A8A294` if used at <14px.
- Skip-to-content link.
- `metadata` export in `layout.tsx` with title, description, OG tags.
- Generate `sitemap.ts` and `robots.ts`.
- `alt` on every image; decorative orbs get `alt=""` + `aria-hidden`.

## 8. Deploy

1. `git init` inside `zenved-site`, initial commit.
2. Create GitHub repo, push.
3. `vercel` CLI or Vercel dashboard → import repo → zero config → deploy.
4. Add custom domain later.

## 9. Order of execution (what I will do when you say "start")

1. Run scaffold command (§1).
2. Read the full prototype HTML in chunks; finalize component list (§4).
3. Port design tokens to `globals.css` (§2).
4. Wire `next/font` for three families (§3.1), expose as CSS vars.
5. Build `layout.tsx` with metadata + font vars + base body styles.
6. Pre-blur orbs once, save WebP to `public/orbs/` (§3.3).
7. Port sections one-by-one into components; compose in `app/page.tsx`.
8. Add Lenis + GSAP reveals behind reduced-motion guard (§3.8).
9. Add `sitemap.ts`, `robots.ts`, `opengraph-image.tsx`.
10. `npm run build` — verify all routes static, check bundle size.
11. Commit, push, connect Vercel.

## 10. Non-goals (explicitly out of scope)

- No backend, no database, no auth, no API routes.
- No CMS integration (content is hardcoded from prototype; can swap to MDX later if needed).
- No i18n, no dark/light toggle (design is dark-only by intent).
- No analytics yet (add Vercel Analytics post-launch if desired).

---

When you say "start", I execute §9 in order. I will check in after step 2 with the finalized component list before writing code.
