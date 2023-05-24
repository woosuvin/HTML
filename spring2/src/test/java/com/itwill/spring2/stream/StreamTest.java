package com.itwill.spring2.stream;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

// 스프링 컨텍스(application-context.xml 또는 servlet-context.xml)를 사용하지 않는 단위 테스트는
// @ExtendWith, @ContextConfiguration 애너테이션을 사용할 필요가 없음.
public class StreamTest {
    
    @Test
    public void test() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
        System.out.println(numbers);
        
        // numbers에서 홀수들만 필터링한 결과:
        List<Integer> odds = numbers.stream().filter((x) -> { return x % 2 == 1; }).toList(); // .filter(x -> x % 2 == 1)
        System.out.println("odds = " + odds);
        
        // numbers의 원소들의 제곱들로 이루어진 리스드:
        List<Integer> squares = numbers.stream().map((x) -> { return x * x; }).toList(); // .map(x -> x * x)
        System.out.println("squares = " + squares);
        
        // numbers의 원소들 중 홀수들의 제곱
        List<Integer> oddSquares = numbers.stream().filter(x -> x % 2 == 1).map(x -> x * x).toList();
        System.out.println("oddSquares = " + oddSquares);
        
        List<String> languages = Arrays.asList("Java", "SQL", "JavaScript");
        System.out.println(languages);
        
        // lnaguages가 가지고 있는 문자열들의 길이를 원소로 갖는 리스트: 
        // argument가 하나이고,,,
        List<Integer> length = languages.stream().map(String::length).toList(); // .map(x -> x.length())
        System.out.println("length = " + length);
        
        List<LocalDateTime> times = Arrays.asList(
                LocalDateTime.of(2023, 5, 24, 11, 30, 0),
                LocalDateTime.of(2023, 5, 23, 12, 35, 0),
                LocalDateTime.of(2023, 5, 25, 17, 00, 0)
        );
        System.out.println(times);
        
        // LocalDateTime 타입을 모두 timestamp 타입으로 변환
        List<Timestamp> timestamps = times.stream().map(x -> Timestamp.valueOf(x)).toList();
        System.out.println("timestamps = " + timestamps);
        
        List<Timestamp> timestamps2 = times.stream().map(Timestamp::valueOf).toList();
        System.out.println("timestamps2 = " + timestamps2);
    }
}
