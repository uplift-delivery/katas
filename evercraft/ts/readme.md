# Welcome to Evercraft

## Original Kata By

Guy Royse [(@guyroyse)](https://twitter.com/guyroyse) and George Walters II [(@walterg2)](https://twitter.com/walterg2) here https://github.com/PuttingTheDnDInTDD/EverCraft-Kata

## Additions Taken from

[Steve Turley](https://github.com/sjturley/evercraft-atdd)

# What is Evercraft?

Evercraft is a DnD style game that you will build one feature at a time.

# Feature: Character Creation

```gherkin
Given a new Evercraft world
When I create a character
Then my character should be added to the world

Given a newly created character
When I distribute my initial attributes
Then I should be in the game
```

## Notes
- Players should be able to provide the following for their character
    - Name
    - Alignment: Good, Evil, Neutral
    - Attribute Levels:
        - Strength, Dexterity, Constitution, Wisdom, Intelligence, Charisma
        - Attributes range from 8 to 20 (default is 8)
        - User should be allowed to spend 30 points as they wish
        - As an attribute increases it becomes more expensive
- Players also have hit points and armor ratings.
  - Hit points defaults to: 10
  - Armor defaults to: 10

### Player Attributes

| Current Attribute | Cost for increase |
|:-----------------:|:-----------------:|
|       __8__       |         1         |
|       __9__       |         1         |
|      __10__       |         1         |
|      __12__       |         1         |
|      __13__       |         1         |
|      __14__       |         2         |
|      __15__       |         2         |
|      __16__       |         3         |
|      __17__       |         3         |
|      __18__       |         4         |
|      __19__       |         4         |
|      __20__       |        N/A        |

# Feature: Fighting

```gherkin
Given an opponent
When I attack
Then I should do damage to my opponent
```

## Notes
- Attacking involves rolling a 20 sided die.
  - In order to inflict damage a roll must be greater than the armor of the opponent
  - Successful attacks deal 1 damage to the opponents hit points.
  - Rolling a natural 20 the opponent should take double damage
  - When a character's hit points are 0 or less the character is considered dead
    - Negative hit points are not allowed.

# Feature: Modifiers

```gherkin
Given a character with high attrigutes
When I engage in battle
Then I receive bonuses during battle
```

## Notes
- Strength: dice roll
- Dexterity: armor
- Constitution: hit points

### Modifier Effects

| Attribute Leve | Modifier |
|:--------------:|:--------:|
|     __8__      |    -1    | 
|     __9__      |    -1    | 
|     __10__     |    0     | 
|     __11__     |    0     | 
|     __12__     |    1     | 
|     __13__     |    1     | 
|     __14__     |    2     | 
|     __15__     |    2     | 
|     __16__     |    3     | 
|     __17__     |    3     | 
|     __18__     |    4     | 
|     __19__     |    4     | 
|     __20__     |    5     | 
