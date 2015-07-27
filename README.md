Request Replay
==============

When making API changes, it's nice to know that you wouldn't affect
current integrations. To attempt to validate this, we'll build a tool
to replay a series of previously-captured API requests.

There are several YAML files containing a series of requests and
responses from the Stripe API. Write a script which, given one of
these files, can replay the requests.
