# 📋 Insurance Claims Processing Kata

## The Problem

You’ve been asked to implement the core logic for an insurance claims processing system. The system should evaluate incoming claims based on a few business rules and return a final payout amount. This version of the kata is designed to be completed in 90 minutes or less and focuses on essential logic and test-driven development (TDD).

---

## Business Requirements

### Submitting a Claim

**As an** insured person, **I want** to submit a claim with incident details, **so that** I can receive a payout.

Each claim has:
- `policyId`
- `incidentType` (e.g., accident, theft, fire, water damage)
- `incidentDate`
- `amountClaimed`

Example:
```ts
const exampleClaim = {
  policyId: 'POL123',
  incidentType: 'fire',
  incidentDate: new Date('2023-06-15'),
  amountClaimed: 3000,
};
```

---

### Example Policy Data

To help support your solution, here is an example of what policies might look like:

```ts
type IncidentType = 'accident' | 'theft' | 'fire' | 'water damage';

interface Policy {
  policyId: string;
  startDate: Date;
  endDate: Date;
  deductible: number;
  coverageLimit: number;
  coveredIncidents: IncidentType[];
}

const examplePolicies: Policy[] = [
  {
    policyId: 'POL123',
    startDate: new Date('2023-01-01'),
    endDate: new Date('2024-01-01'),
    deductible: 500,
    coverageLimit: 10000,
    coveredIncidents: ['accident', 'fire'],
  },
  {
    policyId: 'POL456',
    startDate: new Date('2022-06-01'),
    endDate: new Date('2025-06-01'),
    deductible: 250,
    coverageLimit: 50000,
    coveredIncidents: ['accident', 'theft', 'fire', 'water damage'],
  },
];
```

---

### Evaluating a Claim

Each claim evaluation should return a result with the following fields:

- `approved: boolean` – Was the claim accepted?
- `payout: number` – Final payout amount
- `reasonCode: string` – Explanation code (e.g., `APPROVED`, `POLICY_INACTIVE`, `NOT_COVERED`, `ZERO_PAYOUT`)

---

### Business Rules

- The policy must be active on the `incidentDate`
- The `incidentType` must be included in the policy’s `coveredIncidents`
- Payout = `amountClaimed - deductible`
- If payout is zero or negative, return 0 with reasonCode: `ZERO_PAYOUT`
- The payout may not exceed the `coverageLimit`

---

