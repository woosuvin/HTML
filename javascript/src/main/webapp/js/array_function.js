/**
 * array_function.js
 */

document.addEventListener('DOMContentLoaded', function () {
    // 배열 concat() 메서드: 배열에 새로운 원소를 끝에 추가. 원소가 추가된 새로운 배열을 리턴.
    // 배열 push() 메서드: 기존 배열의 끝에 원소를 추가. 리턴값은 없음.
    const arr = []; // 빈 배열 선언.
    console.log(arr); //[]
    arr.push(100);
    console.log(arr); //[100]
    arr.push(200);
    console.log(arr); //[100, 200]
    
    let arr2 = [];
    console.log(arr2); //[]
    arr2 = arr2.concat(1);
    console.log(arr2); //[1]
    arr2 = arr2.concat(2);
    console.log(arr2); //[1, 2]
    
    
    const numbers = [1, 2, 3, 4, 5, 6, 7];
    
    // 1. 배열 numbers의 원소들 중에서 홀수들만 원소로 갖는 배열을 만들고 출력.
    let odds = []; // 홀수들을 저장할 배열 선언.
    for(let x of numbers) { // 배열의 원소를 순서대로 반복하면서
        if (x % 2) { // 나머지가 있으면(홀수이면)
            odds = odds.concat(x); // 홀수를 추가한 새로운 배열 생성.
        }
    }
    console.log(odds);
    
    odds = numbers.filter((x) => x % 2);
    console.log(odds);
    
    
    // 2. 배열 numbers의 원소를 제곱한 숫자들을 원소로 갖는 배열을 만들고 출력.
    let square = [];
    for(let x of numbers) {
        square = square.concat(x**2); // **: 거듭제곱 연산자
    }
    console.log(square);
    
    square = numbers.map((x) => x**2);
    console.log(square);
    
    
    // 3. 배열 numbers의 원소들 중에서 홀수의 제곱을 원소로 갖는 배열을 만들고 출력.
    let oddSquare = [];
    for(let x of numbers) {
        if (x % 2) {
            oddSquare = oddSquare.concat(x*x);
        }
    }
    console.log(oddSquare);
    
    oddSquare = numbers.filter((x) => x%2).map((x) => x**2);
    console.log(oddSquare);
    
});