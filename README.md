# paymentSubscriptionServicea 

simple Java web app which has a single endpoint to allow users to create a subscription. The endpoint accepts as its input the information described above, saves it in an in-memory data structure and returns a subscription object that holds:
1. the amount to charge per billing
2. the invoice type
3. all the dates that an invoice will be created
Example: if we created a $20 weekly subscription that will charge a customer every Tuesday from the 1st of Feb 2018 till the 1st of March 2018, the response would be as follows:
{
  "id": "44335d51-265a-4e01-ad4d-306be659a48f", 
  "amount": {
    "value": 20,
    "currency": "AUD" 
   },
  "subscription_type": "WEEKLY",
  "invoice_dates": ["06/02/2018", "13/02/2018", "20/02/2018", "27/02/2018"]
}
