Request Replay
==============

When making API changes, it's nice to know that you wouldn't affect
current integrations. To attempt to validate this, we'll build a tool
to capture and subsequently replay a series of API requests.

We've included a script, `requestgen.py`, which can generate various
series of requests to the Stripe API. Or, by passing it a 2nd command
line argument, you can redirect the requests to an HTTP server of your
choosing.
