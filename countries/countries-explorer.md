# Countries Explorer

## The Problem

We’ve been asked to create a responsive frontend interface that allows people to explore and learn about countries around the world using a public API. The goal is to build a clean, performant, and accessible UI that supports browsing, and navigating through real-world data.

The project should be built using any modern frontend framework (e.g., React, Vue, Svelte) and must consume data from the [REST Countries API](https://restcountries.com/).

---

## Requirements

### Browsing Countries

**As a** curious person, **I want** to scroll through a list of countries, **so that** I can see what’s out there.

- Fetch and display a paginated or infinite-scrolling list of countries.
- Each country should show at least its name, flag, population, region, and capital.

---

### Country Details View

**As a** user, **I want** to click on a country, **so that** I can see more information about it.

- Display detailed info such as native name, subregion, timezones, currencies, languages, and borders.
- Implement a back button to return to the country list.

---

### Favorites

**As a** user, **I want** to favorite countries I care about, **so that** I can quickly revisit them later.

- Allow users to mark/unmark countries as favorites.
- If I leave the app and come back it should be persisted
- Provide a dedicated view or section that lists all favorite countries.
- Indicate which countries are favorited in both the list and details views.

---

### Responsive Design

**As a** person on the go, **I want** the UI to adapt to my device, **so that** it’s always easy to use.

- Use flexible layout techniques (grid, flexbox).
- Avoid fixed widths or pixel-specific sizing.

---
