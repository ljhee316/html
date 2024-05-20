/**
 * array_method.html 에 포함
 * 
 * js array 객체의 함수(메서드)들
 * 
 */

const arr = [1,2,3];  //arr=[1,2,3,4]   재할당은안됨. 
console.log(arr);

//배열에 새로운 원소를 배열 끝에 추가: 
arr.push(100); //push():원본 배열의 끝에 새로운 원소를 추가, 원본 배열이 바뀜!!!! 객체는그대로!! 주소값(const)은 그대로 원소값만 바뀜.
console.log(arr); //[1, 2, 3, 100]

let result = arr.concat(200);//원본배열을 변경하지 않고!!!! 원소가 추가된 새로운 배열을 리턴함. ->result 값에 따로 저장을 해줘야함.
console.log(arr); //[1, 2, 3, 100]
console.log(result); //[1, 2, 3, 100, 200]


//배열의 마지막 원소를 삭제:
arr.pop(); //pop(): 원본배열의 마지막 원소를 삭제, 원본 배열의 내용이 바뀜!!!
console.log(arr);//[1, 2, 3]

//slice(start,end): 배열에서 start인덱스부터 end 인덱스까지 잘라낸 새로운 배열을 리턴!!! 원본배열이 변경되지않음!!!.  (arr[-1] 마지막원소)
result = arr.slice(0,-1);
console.log(arr);// [1, 2, 3]
console.log(result);//[1, 2]


//toSorted(): 배열의 원소들을 문자열로 변환해서 크기 비교를 함
//오름차순 정렬된 새로운 배열을 리턴.
//원본배열을 변경되지 않음!!!
//toSorted(callback): 배열원소들의 크기비교를 할때 사용할 콜백을 아규먼트로 전달.
const arr2 = [45,23,90,21,5];
console.log(arr2);
result = arr2.toSorted((x, y) => x - y);  //각각원소들 다 비교,
console.log(arr2);  
console.log(result);

//sort():
//배열의 원소들을 문자열로 변환해서 크기 비교.
//원본 배열의 원소순서를 오름차순으로 정렬, 원본 배열이 바뀜!!!
//sort(callback): 배열원소들의 크기 비교에서 사용하는 콜백을 아규먼트로 전달.
arr2.sort((x, y) => x - y);  
console.log(arr2);  


//forEach, filter, map, reduce
const numbers = [1,2,3,4,5,6];
console.log(numbers);
//배열numbers 원소들 중에서 홀수만 있는 새로운배열만들기
const odds = [];  //let odds = [];
for(let x of numbers) {
    if(x % 2) {
        odds.push(x);  //odds = odds.concat(x);   -->concat은 재할당해야하기때문에 let으로 배열 선언해야함!
    }
}
console.log(odds);

//filter  화살표함수
result = numbers.filter((x) => x % 2);
console.log(result);

// 익명함수
result = numbers.filter(function (x) {
    return x % 2;
})


//map 맵핑.
//배열numbers에 원소들의 제곱을 원소로 갖는 새로운 배열만들기
const squares = [];
for(let x of numbers) {
    squares.push(x * x);
}
console.log(squares);

//map이용
result = numbers.map((x) => x * x);
console.log(result);

//forEach
result.forEach((x) => console.log(x));  //for문을 간단하게.


//reduce 
//배열 numbers의 모든 원소들의 합계
console.log(numbers);

let sum = 0;
for(let value of numbers) {
    sum += value;
}
console.log(`sum = ${sum}`);

//reduce(callback, initiaValue)
sum = numbers.reduce((acc, cur) => acc + cur, 0);
console.log(`sum = ${sum}`);

//numbers 의 모든 원소들의 곱:
let mul = numbers.reduce((acc, cur) => acc * cur, 1);
console.log(`mul = ${mul}`);


// numbers의 원소들 중에서 짝수들의 합
result = numbers.filter((x) => x % 2 === 0).reduce((acc, cur) => acc + cur, 0);
console.log(`짝수들의 합: ${result}`);

// numbers의 원소들의 제곱의 합
result = numbers.map((x) => x * x).reduce((acc, cur) => acc + cur, 0);
console.log(`제곱의 합 : ${result}`);

// numbers의 원소들 중에서 짝수들의 제곱의 합
result = numbers.filter((x) => x % 2 === 0)
        .map((x) => x * x)
        .reduce((acc, cur) => acc + cur, 0);
console.log(`짝수들의 제곱의 합: ${result}`);


 