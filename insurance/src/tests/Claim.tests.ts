const exampleClaim = {
  policyId: 'POL123',
  incidentType: 'fire',
  incidentDate: new Date('2023-06-15'),
  amountClaimed: 3000,
};
/* test if there is a policy
test if the incident type is a valid type and is there
test if date is there and is formatted correctly
test the amount is a number and not zero or negitive
test the return to see if it returns reason code and approved boolean and otional value
test bad policy string
test if policy 
