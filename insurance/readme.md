# 📋 Insurance Claims Processing Kata

## The Problem

You’ve been asked to implement the core logic for an insurance claims processing system. The system should evaluate incoming claims based on a variety of conditions, apply business rules, and calculate payouts accordingly. The purpose of this kata is to provide a more substantial, non-trivial exercise for practicing test-driven development (TDD). A significant part of the challenge lies in deciding which tests to write—and in what order. Your solution could be implemented as a class with methods, or expanded into a full REST API with multiple endpoints, depending on how far you'd like to take it.

---

## Business Requirements

### Submitting a Claim

**As an** insured person, **I want** to submit a claim with incident details, **so that** I can recieve a payout.

Each claim has:
- `policyId`
- `incidentType` (e.g., accident, theft, fire, water damage)
- `incidentDate`
- `amountClaimed`
- `description`

---

### Example Policy Data

To help support your solution, here is an example of what policies might look like:

```ts
type IncidentType = 'accident' | 'theft' | 'fire' | 'water damage';

interface Policy {
  policyId: string;
  type: 'basic' | 'premium' | 'comprehensive';
  startDate: Date;
  endDate: Date;
  deductible: number;
  coverageLimit: number;
  totalClaimed: number;
  coveredIncidents: IncidentType[];
}

const examplePolicies: Policy[] = [
  {
    policyId: 'POL123',
    type: 'basic',
    startDate: new Date('2023-01-01'),
    endDate: new Date('2024-01-01'),
    deductible: 500,
    coverageLimit: 10000,
    totalClaimed: 2000,
    coveredIncidents: ['accident', 'fire'],
  },
  {
    policyId: 'POL456',
    type: 'comprehensive',
    startDate: new Date('2022-06-01'),
    endDate: new Date('2025-06-01'),
    deductible: 250,
    coverageLimit: 50000,
    totalClaimed: 5000,
    coveredIncidents: ['accident', 'theft', 'fire', 'water damage'],
  },
  {
    policyId: 'POL789',
    type: 'premium',
    startDate: new Date('2023-05-15'),
    endDate: new Date('2026-05-15'),
    deductible: 100,
    coverageLimit: 100000,
    totalClaimed: 10000,
    coveredIncidents: ['theft', 'fire'],
  },
];
```

---

### Evaluating a Claim

**Each claim evaluation should return an object with the following fields:**

- `approved: boolean` – Was the claim accepted?
    
- `escalated: boolean` – Was it flagged for manual review?
    
- `payout: number` – Final payout amount (after deductible and limits)
    
- `reasonCode: string` – A short code explaining the decision (e.g., `APPROVED`, `POLICY_INACTIVE`, `ESCALATED_FOR_REVIEW`)

---

**As a** claims adjuster, **I want** to evaluate incoming claims, **so that** we can approve, deny, or escalate them.

Apply the following rules:
- Check if the policy is active on the incident date
- Some incident types (e.g. theft) require a police report
- Claims above $10,000 must be escalated for manual review
- Certain policy types (e.g. “basic”) don’t cover some incident types
- Deductibles should be subtracted from the claim payout

---

### Calculating Payout

**As a** claims adjuster, **I want** to determine how much to reimburse, **so that** customers are paid fairly.

- A valid claim results in a payout equal to `amountClaimed - deductible`
- The payout cannot exceed the policy’s coverage limit
- If the result of amountClaimed - deductible (remaining balance) is zero or negative, the payout is $0
- Store the payout decision with a reason code

---

### Claims History & Limits

**As an** insured person, **I want** to see how much I've claimed, **so that** I know my coverage usage.

- Total claim amount should not exceed the policy’s coverage limit
- The system should track total claimed vs. remaining coverage

---


