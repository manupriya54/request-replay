#!/usr/bin/env python

# Generate various interesting series of requests to the Stripe
# API.
#
# Notable goals: portability, lack of dependencies. Notable
# anti-goals: security, perfectly correct interfaces (i.e. we
# deliberately don't care that we're not verifying SSL certs, we
# ignore things like encoding headers since Stripe always returns
# UTF-8)
#
# This should work on any Python 2.6 or newer (including Python 3)

from __future__ import print_function, unicode_literals

import datetime
import json
import os
import sys

try:
    from urllib import quote_plus
except ImportError:
    from urllib.parse import quote_plus

try:
    from urllib2 import Request, urlopen, HTTPError
except ImportError:
    from urllib.request import Request, urlopen, HTTPError

API_KEY = 'sk_test_xOEwT736mUYLIx7hs0xTiCkI'
API_HOST = 'https://api.stripe.com'

BASE_CARD = {
    'number': '4242424242424242',
    'exp_month': '12',
    'exp_year': str(datetime.datetime.now().year + 1),
    'cvc': '123',
}

# Helpers

def urlencode_nested(body):
    def format_keys(keys):
        keys = [quote_plus(str(k)) for k in keys]
        if len(keys) < 2:
            return keys[0]
        else:
            return '%s[%s]' % (keys[0], ']['.join(keys[1:]))

    def recurse(d, prefix, pairs):
        for k, v in d.items():
            p = prefix + [k]
            if isinstance(v, dict):
                recurse(v, p, pairs)
            else:
                pairs.append((format_keys(p), quote_plus(str(v))))
        return pairs

    return '&'.join('='.join(p) for p in recurse(body, [], []))

def request(path, method=None, body=None):
    headers = {'Authorization': 'Bearer %s' % (API_KEY,)}
    if body is not None:
        body = urlencode_nested(body).encode('utf-8')
    req = Request(API_HOST + path, body, headers)
    if method:
        req.get_method = lambda: method
    f = urlopen(req)
    return json.loads(f.read().decode('utf-8'))

def make_card(**extra):
    c = BASE_CARD.copy()
    c.update(extra)
    return c

# API calls

def create_token(card, **kwargs):
    kwargs.update(card=card)
    return request('/v1/tokens', body=kwargs)

def create_charge(amount, currency, **kwargs):
    kwargs.update(amount=amount, currency=currency)
    return request('/v1/charges', body=kwargs)

def retrieve_charge(charge):
    return request('/v1/charges/%s' % (quote_plus(charge),))

def refund_charge(charge, amount=None, **kwargs):
    if amount:
        kwargs.update(amount=amount)
    return request('/v1/charges/%s/refunds' % (quote_plus(charge),), method='POST', body=kwargs)

# Request sequences

def sequence_simple():
    create_charge(amount=123, currency='usd', card=make_card())
    create_charge(amount=10000, currency='usd', card=make_card(address_line1='3180 18th St', address_zip='94110'))
    try:
        create_charge(amount=1999, currency='usd', card=make_card(number='4000000000000001'))
    except HTTPError as e:
        # should be a 402 decline
        pass

def main():
    global API_HOST

    if len(sys.argv) < 2:
        sys.stderr.write("Usage: %s <simple> [api_host]\n" % (sys.argv[0],))
        return

    if len(sys.argv) > 2:
        host = sys.argv[2]
        if host.endswith('/'):
            host = host[:-1]
        API_HOST = host

    cmd = sys.argv[1]
    if cmd == 'simple':
        sequence_simple()
    else:
        sys.stderr.write("Unknown sequence '%q'" % (cmd,))

if __name__ == '__main__':
    main()
