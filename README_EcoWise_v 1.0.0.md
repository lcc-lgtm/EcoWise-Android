# EcoWise (Version 1.0)

EcoWise is a modern Android application built with **Jetpack Compose**, combining local offline-first capabilities (**Room / SQLite**) with remote cloud persistence (**Supabase**). 

This release marks a major architectural milestone, overcoming several critical logical and structural challenges during its development and integration lifecycle.

---

## Key Architectural Challenges & Solutions

### 1. Hybrid Authentication Architecture
* **Challenge:** Designing a seamless system that coordinates between offline-first local storage and cloud persistence.
* **Solution:** Established a dual-layer synchronization pattern, ensuring that the app functions reliably under varying network conditions by leveraging local Room caching alongside remote Supabase operations.

### 2. Cloud Schema Initialization
* **Challenge:** Initial remote deployment faced unexpected failures due to an uninitialized Supabase backend instance lacking the required table schemas.
* **Solution:** Performed database reconnaissance, designed and executed a clean SQL initialization schema (`user_table`), and established robust Row Level Security (RLS) access policies.

### 3. Data Integrity & Sync Flaws
* **Challenge:** Existing users experienced authentication failures during `Sign-In` because password payloads were omitted during remote synchronization (defaulting to empty strings `""`). Furthermore, local Room caches and remote Supabase environments required independent state handling.
* **Solution:** Patched repository data flows to securely preserve and transmit user credentials (`password = user.password`), closing the data integrity gap between local and remote environments.

### 4. Resilient Fallback Mechanism
* **Challenge:** Strict remote-only validation caused sign-in lockouts when network partitions or edge-case synchronization discrepancies occurred.
* **Solution:** Implemented a robust local Room fallback verification mechanism within `signInUser`, guaranteeing continuous, fault-tolerant sign-in capabilities for registered users.

---

## Tech Stack
* **UI Framework:** Jetpack Compose
* **Local Database:** Room (SQLite)
* **Backend & Cloud Persistence:** Supabase (PostgreSQL REST / Auth)
* **Language:** Kotlin
