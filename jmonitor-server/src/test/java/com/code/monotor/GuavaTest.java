package com.code.monotor;

import com.google.common.base.*;
import com.google.common.base.Objects;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.*;

import static com.google.common.collect.ImmutableMap.of;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

/**
 * @author codeDorado
 * @version 1.0
 * @date 2021/3/21 22:45
 */
public class GuavaTest {

    List<String> list = Arrays.asList(
            "one", "two", null, "three"
    );
    Map<String, String> stringMap = of("hello", "world");

    @Test
    public void joinerTest() {
        stringMap.forEach((k, v) -> {
            System.out.println(k + " " + v);
        });
        StringBuilder builder = new StringBuilder();
        StringBuilder appendTo = Joiner.on("#").skipNulls().appendTo(builder, list);
        assertThat(builder, equalTo(appendTo));
    }


    @Test
    public void splitTest() {
        ArrayList<String> strings = Lists.newArrayList(Splitter.on(',').omitEmptyStrings().trimResults()
                .split("i am , your father, yes? ok, yes"));

        strings.forEach(System.out::println);
    }

    @Test
    public void charMatcherTest() {
        CharMatcher.whitespace().negate();
    }


    @Test
    public void optionalTest() {
        // Object o = Optional.ofNullable(null).orElse("123");
        // System.out.println(o);
        System.out.println(Optional.fromNullable(null).or(Lists.newArrayList()));
    }

}
