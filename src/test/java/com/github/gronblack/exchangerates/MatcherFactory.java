package com.github.gronblack.exchangerates;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.function.BiConsumer;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Factory for creating test matchers.
 * Comparing actual and expected objects via AssertJ
 * Support converting json MvcResult to objects for comparison.
 */
public class MatcherFactory {

    public static <T> Matcher<T> usingAssertions(Class<T> clazz, BiConsumer<T, T> assertion, BiConsumer<Iterable<T>, Iterable<T>> iterableAssertion) {
        return new Matcher<>(clazz, assertion, iterableAssertion);
    }

    public static <T> Matcher<T> usingEqualsComparator(Class<T> clazz) {
        return usingAssertions(clazz,
                (a, e) -> assertThat(a).isEqualTo(e),
                (a, e) -> assertThat(a).isEqualTo(e));
    }

    public static class Matcher<T> {
        private final Class<T> clazz;
        private final BiConsumer<T, T> assertion;
        private final BiConsumer<Iterable<T>, Iterable<T>> iterableAssertion;
        private static final ObjectMapper mapper = new ObjectMapper();

        private Matcher(Class<T> clazz, BiConsumer<T, T> assertion, BiConsumer<Iterable<T>, Iterable<T>> iterableAssertion) {
            this.clazz = clazz;
            this.assertion = assertion;
            this.iterableAssertion = iterableAssertion;
        }

        public void assertMatch(T actual, T expected) {
            assertion.accept(actual, expected);
        }

        public void assertMatch(Iterable<T> actual, Iterable<T> expected) {
            iterableAssertion.accept(actual, expected);
        }

        public ResultMatcher contentJson(T expected) {
            return result -> assertMatch(readValue(getContent(result), clazz), expected);
        }

        @SafeVarargs
        public final ResultMatcher contentJson(T... expected) {
            return contentJson(List.of(expected));
        }

        public ResultMatcher contentJson(Iterable<T> expected) {
            return result -> assertMatch(readValues(getContent(result), clazz), expected);
        }

        private static String getContent(MvcResult result) throws UnsupportedEncodingException {
            return result.getResponse().getContentAsString();
        }

        private static <T> T readValue(String json, Class<T> clazz) {
            try {
                return mapper.readValue(json, clazz);
            } catch (IOException e) {
                throw new IllegalArgumentException("Invalid read from JSON:\n'" + json + "'", e);
            }
        }

        private static <T> List<T> readValues(String json, Class<T> clazz) {
            ObjectReader reader = mapper.readerFor(clazz);
            try {
                return reader.<T>readValues(json).readAll();
            } catch (IOException e) {
                throw new IllegalArgumentException("Invalid read array from JSON:\n'" + json + "'", e);
            }
        }
    }
}
