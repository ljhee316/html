/**
 * array.html에 포함
 * 자바스크립트 배열: 여러개의 원소(아이템)들을 하나의 변수 이름으로 저장하기 위한 타입.
 * 자바배열: "한가지" 타입의 값들 여러개를 저장하는 타입.
 * 자바스크립트 배열에서는 다른 타입의 값들이 저장될 수 있음.
 * 
 */


//div#output요소찾기
const output = document.querySelector('div#output');

//배열선언과 초기화
const arr = [1, 2, 30, 40,-5];  //자바 int[] arr = {1,2,3};

//배열 arr의 내용을 output영역에 씀.
let html='';
for(let i = 0; i < arr.length; i++) {
    html += `${arr[i]}, `;
}
output.innerHTML += html + '<br/>';



//자바 향상된 for문장: for(변수선언: 배열) {...}
//자바스크립트 향상돤 for문장 1 : for-of 문장  --> 배열의 원소들을 iteration(순회). 값만 필요할때.
html ='';
for(let val of arr) {
    html += `${val}, `;
}
output.innerHTML += html + '<br/>';

//자바스크립트 향상돤 for문장 2 : for-in 문장  --> 배열의 인덱스들을 iteration(순회). 인덱스와 값을 둘다 필요할때.
html = '';
for(let idx in arr) {
    html += `${idx} : ${arr[idx]}, `;  //  0 : 1, 1 : 2, 2 : 30, 3 : 40, 4 : -5,
}
output.innerHTML += html + '<br/>';



//배열 arr의 원소들의 합계와 평균을 output에 출력.
html='';
let sum = 0;
for(let val of arr) {
    sum += val;
}
const avg = sum / arr.length;// js에서 /(나누기)는 소수점이하까지 계산함.

output.innerHTML += `sum=${sum}, average=${avg} <br/>`; //sum=68, average=13.6 js는 정수,실수 구분하지않음.



//배열의 원소들을 ul의 li요소로 만들기
const drama = ['삼식이 삼촌', '동조자', '삼체'];
html='';
for(let val of drama) {
    html += `<li>${val}</li>`;
}
output.innerHTML += html;