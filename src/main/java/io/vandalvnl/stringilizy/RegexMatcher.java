package io.vandalvnl.stringilizy;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

class RegexMatcher {
  /**
   * All accented characters in the Portuguese alphabet, in UPPER CASE
   */
  static final String ALL_SPECIAL_UPPER_CASE = "A-ZÇÁÀÃÂÉÈẼÊÌÍĨÎÕÓÒÔŨÚÙÜÛ.";
  /**
   * All accented characters in the Portuguese alphabet, in lower case
   */
  static final String ALL_SPECIAL_LOWER_CASE = "a-zçáàãâéèẽêìíĩîõóòôũúùüû.";

  /**
   * Just regex for characters in ALL_SPECIAL...in cases where groups covering the
   */
  static final String REGEX_ALL_UPPER_CASE = "[" + ALL_SPECIAL_UPPER_CASE + "]";
  /**
   * Same for REGEX_ALL_UPPER_CASE
   */
  static final String REGEX_ALL_LOWER_CASE = "[" + ALL_SPECIAL_LOWER_CASE + "]";

  static final String[] HTML_ESCAPED = new String[] { "&amp;", "&lt;", "&gt;", "&quot;", "&#x27;", "&#x2F;" };

  static final String[] HTML_UNSAFE = new String[] { "&", "<", ">", "\"", "'", "/" };

  /**
   * GetMatcher provide a compiled regex to Slug/Camel/Sneak Case. Inspired by
   * http://30secondsofcode.org
   *
   * @param string
   * @return
   */
  static Matcher getMatcher(String string) {
    return Pattern
        .compile(REGEX_ALL_UPPER_CASE + "{2,}(?=" + REGEX_ALL_UPPER_CASE + REGEX_ALL_LOWER_CASE + "+[0-9]*|\\b)|"
            + REGEX_ALL_UPPER_CASE + "?" + REGEX_ALL_LOWER_CASE + "+[0-9]*|" + REGEX_ALL_UPPER_CASE + "|[0-9]+")
        .matcher(string);
  }
}
