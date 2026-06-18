# AGENTS.md — Mini-Terraform

## Project State (Jun 2026)

- **Greenfield**: No `src/` directory, no Java files, no commits yet.
- Only root files: `build.gradle.kts`, `settings.gradle.kts` (empty), `.git/`.
- No `.gitignore`, no Gradle wrapper, no linter/formatter configs.

## Authoritative Plans

All design and task tracking lives in `Guides/`:
- `Guides/IDEA.md` — conceptual OOP architecture overview
- `Guides/PLAN.md` — granular, actionable checklist with file paths, class names, and test expectations

Consult `PLAN.md` before any implementation phase. It defines the exact files to create and what each should do.

## Build System

- **Gradle (Kotlin DSL)**, Java 17+, package `minitf`, main class `minitf.cli.MiniTfCli`
- Gradle wrapper is not yet committed — run `gradle wrapper` before first build
- No dependencies declared yet; PLAN.md specifies JUnit 5 + Gson (or org.json)
- Expected commands:
  - `./gradlew build` — full build
  - `./gradlew test` — all tests (unit + integration)
  - `./gradlew run --args="<command>"` — CLI execution

## Architecture (from PLAN.md)

```
src/main/java/minitf/
├── core/       Provisionable, CloudResource, EC2, VPC, S3, result types
├── state/      StateStore, StateManager (JSON persistence)
├── engine/     ResourceEngine (diff/plan/apply), Action, Plan
├── provider/   MockProvider (latency/failure simulation)
└── cli/        MiniTfCli (main), DesiredConfigParser
src/test/java/minitf/ mirrors main package tree
```

## Workflow Gotchas

- **Plan before build**: Phase 0 (scaffolding) must finish before any Java code — wrapper, `.gitignore`, build config deps, and project directories.
- **State file**: `terraform-state.json` is generated at runtime in the working dir — add to `.gitignore`.
- **Polymorphic JSON**: `StateManager` needs a Gson type adapter for `CloudResource` subclasses — this is a known tricky spot.
- **No CI/CD yet**: None configured; PLAN.md has a verification checklist at the bottom for manual sign-off.
