/**
 * object.html에 포함
 */

 // JSON(javascript object notation) : 자바스크립트 객체 표기법
 // {property: value,...}  !!!콜론: 사용해야함!
 
 const person = {
    name: '홍길동',
    age: 16,
    phone: ['010-0000-0000', '02-0000-0000']
 };
 
 console.log(person);
 
 
 //객체가 가지고 있는 프로퍼티 접근:(1)참조연산자, (2)인덱스 연산자
 console.log(person.name);  //참조연산자
 console.log(person['age']);  //인덱스 연산자  -->인덱스 문자열로 써줘야함.[프로퍼티이름을 써줘야함.]
 console.log(person.phone[0]);  //참조연사자로 배열에서 원하는것 찾기.  ==>(person['phone'][0])
 console.log(person['phone'][0]);
 
 person.age = 17; //객체의 프로퍼티 값 변경.
 console.log(person);
 
 
 
 //자바스크립트의 객체는 객체가 생성된 이후에 프로퍼티를 추가할수 있음!!!
 person.email ='hgd@itwill.com';
 console.log(person);
 
 //메서드를 갖는 객체: 
 const score = {
    html: 100,
    css: 90,
    js: 81,
    sum: function() {
      return this.html + this.css+ this.js //객체의 프로퍼티를 접근할때 this키워드 사용
    },
    mean: function() {
      return this.sum() / 3;  
    }    
 };
 
 console.log(score);
 console.log(score.sum());
 console.log(score.mean());
 
 //-->함수 재사용을 위해 생성자(객체생성을 위한)함수를 만듬.
 //생성자 함수(constructor function): this키워드를 사용해서 프로퍼티를 선언함 함수.
 function Score(html, css, js) {
   //필드
   this.html = html;
   this.css = css;
   this.js = js; 
   
   //메서드
   this.sum = function() {
    return this.html + this.css + this.js
   };
   this.mean = function() {
    return this.sum() / 3
   }
 };
 //생성자함수를 사용한 객체생성: new 생성자함수();
 const score1 = new Score(1,2,3);
 console.log(score1);
 console.log(score1.sum());
 console.log(score1.mean());
 
 const score2 = new Score();//->모든 필드는 nudefined가 됨.
 console.log(score2);
 console.log(score2.sum());//->undefined + nudefined = not a number
 

 //자바 스크립트 객체는 for-in 구문에서 사용할 수 있음.-> 프로퍼티 이름들을 iterarion.반복
 const student = {
    no: 101,
    name: '오쌤',
    classNo: 10    
 };
 for(let x in student) {  // x = 문자열로 출력됨 그래서 배열 값 구할때는 []안에 문자열 x를 입력해줘야함.
    console.log(x,':',student[x])
 };


//class키워드
class Rectangle {
    //생성자: 필드 초기화
     constructor(width, height){
        this.width = width;
        this.height = height;
     }
     
     //메서드: function키워드 사용하지않음!!!
     area() {
        return this.width * this.height;
     }
     
     perimeter() {
        return(this.width + this.height) * 2;
     }  
}


// 클래스를 사용한 객체 생성:
const rect1 = new Rectangle(2,3);
console.log(rect1);
console.log(`넓이: ${rect1.area()}`);
console.log(`둘레: ${rect1.perimeter()}`);
 
 
 //circle 클래스선언:
 //필드 radius 기본값 0.
 //메서드 1. 넓이(area) 2, 둘레(perimeter)
 
class Circle {
    constructor(radius = 0) {
        this.radius = radius;
    }
    
    area() {
        return this.radius * this.radius * Math.PI;
    }
    
    perimeter() {
        return 2 * this.radius * Math.PI;
    }
}

//const cir1=new Circle();--> 생성자의 디폴드파라미터가 사용되는 경우.
const cir1= new Circle(10);
console.log(cir1);
console.log(cir1.area());
console.log(cir1.perimeter());
 
 