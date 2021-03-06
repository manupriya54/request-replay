#ifndef CPR_PAYLOAD_H
#define CPR_PAYLOAD_H

#include <memory>
#include <string>
#include <initializer_list>

#include "defines.h"

namespace cpr {

struct Pair {
    template <typename KeyType, typename ValueType,
              typename std::enable_if<!std::is_integral<ValueType>::value, bool>::type = true>
    Pair(KeyType&& p_key, ValueType&& p_value)
            : key{CPR_FWD(p_key)}, value{CPR_FWD(p_value)} {}
    template <typename KeyType>
    Pair(KeyType&& p_key, const int& p_value)
            : key{CPR_FWD(p_key)}, value{std::to_string(p_value)} {}

    std::string key;
    std::string value;
};

class Payload {
  public:
    Payload(const std::initializer_list<Pair>& pairs);
    Payload(const std::string pairs);

    std::string content;
};

} // namespace cpr

#endif
