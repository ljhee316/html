/**
 * function.html에 포함
 * 
 * 자바스크립트 함수 선언(정의)방법:
 * function 함수이름([파라미터,...]) {
 *      실행코드;
 *      [return;]
 * }
 * 
 * 함수이름앞에 리턴타입을 선언하지 않음.
 * 파라미터를 선언할때 const, let, var 와 같은 키워드를 사용하지 않음.
 */



function add(x, y) {
    console.log(`x=${x}, y=${y}`);
    return x + y;
}

let result = add(1,2);
console.log(`result=${result}`);

//js함수는 파라미터의 타입을 검사하지않음.
result = add('hello', 'javascript');
console.log(`result=${result}`);

//js 파라미터의 개수를 검사하지않음.
result = add(1,2,3);
console.log(`result=${result}`);
result = add(1);//x=1, y=undefined-->변수는 있는데 초기화가(할당) 되지않은 변수.
console.log(`result=${result}`);// result=NaN:Not a Number

//js 모든 함수는 아규먼트 속성을 가지고 있음.
function testArgs(){
    console.log(arguments);
    for(let arg of arguments) {
        console.log(arg);
    }
}

testArgs();
testArgs('hello');
testArgs(1,'안녕');



//숫자 2개를 아규먼트로 전달받아서 뺼셈결과 리턴하는 함수
function minus(x, y) {
    console.log(arguments);
    return x - y;
}

result=minus(5,2);
console.log(result);
//result=minus(5,2,1);
//consloe.log(result);



//default parameter:기본값이 설정된 파라미터.
function multiply(x, y = 1) {
    return x * y;
}

result = multiply(2,3);  //아규먼트 y를 전달하면 기본값을 무시.
console.log(`result=${result}`)

result=multiply(2);  //아규먼트 y를 전달하지 않으면 기본값이 사용됨.
console.log(`result=${result}`);


/**
 * js함수는 객체(object)이다.
 * 1.프로퍼티(property)를 가질 수 있음. 예) arguments 
 * 2.변수에 저장할 수 있음.
 * 3.다른함수를 호출할때 아규먼트로 함수(객체)를 전달할 수 있음.
 * 4.함수 내부에서 다른 함수를 선언할 수 있음.
 * 5.함수(객체)를 리턴할 수 있음.
 */


//2.함수객체 add를 변수 plus에 할당(저장).
const plus = add; 
console.log(plus);//->plus 함수객체
console.log(plus(100, 200)); //-->plus(100,200)함수를 호출

//익명함수(anonymous function) 이름이 없는 함수.  -->변수에 저장을 해줘야함!
const divide = function (x, y) {
    return x / y;
};
result=divide(1,2);
console.log(`result=${result}`);

//화살표함수(arrow function):익명함수를 간단히 표현하는 문법
//(param,...) => {...}
//(param,...) => 리턴값
const sudtract = (x, y) => x - y;  //화살표함수 객체를 변수sudtract에 저장.
result=sudtract(1,2);
console.log(`result=${result}`);



//3.함수객체를 아규먼트로 전달받는 함수
function calculate(x, y, op) {
    return op(x, y);
}
console.log(calculate(1,2,add));  //add가 콜백임.
console.log(calculate(1,2,function(x, y) {return x - y;}));  //function(x, y) {return x - y;})가 op변수에 저장이되는거라고 생각하면됨.
console.log(calculate(1,2,(x, y) => x / y)); //화살표함수.   -->이벤트리스너,핸들러에서 많이 사용함.
//콜백(callback):(나중에 호출하기 위해서) 아규먼트로 전달하는 함수.


//4,5 지역(내부)함수: 함수안에서 선언하는 함수.
function increase(n) {
    //지역함수-> 함수안에 함수를 만들때 변수를 이용해서 더 다양한 기능을 만들기위해 지역함수를 만듬.
    //지역함수는 바깥함수의 지역변수(파라미터포함)들을 사용할수 있음.
    function addN(x) {
        return x + n;//-->n 은 지역함수.
    }
    
    return addN;  //함수객체를 리턴함.()괄호가 없기때문에 객체가 리턴됨.
}

const increaseTwo = increase(2);// x+2 를 리턴함;
console.log(increaseTwo);// function addN(x) {return x + 2;}
console.log(increaseTwo(100));// 102

const increaseTen =increase(10);
console.log(increaseTen);// function addN(x) {return x + 10;}
console.log(increaseTen(100));//110

console.log(increase(1)(10));// increase(1): 함수( function addN(x) {return x + 1;} ),함수(10) 호출함.-->increase(1)(10)













