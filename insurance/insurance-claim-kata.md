# 📋 Insurance Claims Processing Kata

## The Problem

You’ve been asked to implement the core logic for an insurance claims processing system. The system should evaluate incoming claims based on a variety of conditions, apply business rules, and calculate payouts accordingly. This challenge will involve complex decision trees and layered conditionals—ideal for exercising clean logic, test coverage, and thoughtful modeling.

---

## Business Requirements

### Submitting a Claim

**As a** person with insurance, **I want** to submit a claim with incident details, **so that** it can be reviewed and processed.

Each claim has:
- `policyId`
- `incidentType` (e.g., accident, theft, fire, water damage)
- `incidentDate`
- `amountClaimed`
- `description`

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

**As a** person with insurance, **I want** to see how much I've claimed, **so that** I know my coverage usage.

- Total claim amount should not exceed the policy’s coverage limit
- The system should track total claimed vs. remaining coverage

---
