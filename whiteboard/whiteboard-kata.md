# Whiteboard Kata

## The Problem

We’ve been contracted by a stationery company to simulate writing on a digital whiteboard application with a marker. This marker must mimic the way a real marker behaves: it dries out with use, has a limited surface area to write on, and can be replaced when used up. The goal is to accurately reflect these behaviors in code so the whiteboard tool can provide a realistic user experience.

---

## Business Requirements

### Writing

**As a** writer, **I want** to get all my messages onto the board, **so that** others can read and understand my thoughts clearly.

As you write, the marker uses up ink—letters use 1 unit, digits use 2 units, and special characters use 3 units. Spaces don’t use any ink at all. Once the marker runs out, it keeps going by leaving blank spaces where characters would have been, but it no longer depletes ink.

For example, writing the sentence "Dinner at 7?" would use 1 unit each for the letters, 2 units for the digit, and 3 units each for the question mark and space. If the marker only has 8 units of ink left, the output might look like: "Dinner  ", with the rest replaced by blank spaces once the ink runs out.

---

### Whiteboard Space

**Given** a whiteboard has only so much room to write, **when** the available space is exhausted, **then** I cannot add any more characters.

The whiteboard has a fixed character space limit. Each character written consumes space based on its type: letters use 1 unit, digits use 1.5 units, and special characters use 2 units. Even when the marker runs out of ink and writes a space instead of a character, it still consumes the same amount of space that the character would have used. Once the board is full, no additional characters—whether visible or not—can be added, and any overflow is ignored.

---

### Replacing Markers

**As a** writer, **I want** to use all my markers, **so that** I can get all my thoughts out.

Each pack contains six markers. When one marker runs out of ink, I want to seamlessly switch to a new one so I can continue writing without interruption—until all six markers are used and no further writing is possible.

---

### Erasing

**As a** writer, **I want** to erase words cleanly, **so that** I can make space for edits or corrections.

When I erase a word, the entire word is removed along with any adjacent spaces. This creates a clean gap on the board. After erasing, I can write new characters in the available space left behind.

---