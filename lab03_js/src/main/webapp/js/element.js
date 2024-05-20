/**
 * element.html에 포함
 */

 //button#btn1인 요소 찾기
 const btn1= document.querySelector('button#btn1');
 //btn1요소에 클릭이벤트리스너를 설정하기
 btn1.addEventListener('click',function() {
    //document.getElementById(id)사용해서 id1인 요소 찾기
    //같은id를 갖는 요소가 여러개가 있으면 가장 먼저 찾은 요소 1개만 리턴.
    const div1 = document.getElementById('id1');
    //div1 바탕색 변경
    div1.style.backgroundColor = 'lime';   
 });

 //#btn2요소찾기
 const btn2 = document.querySelector('button#btn2');
 //#btn2에 클릭이벤트 리스너 설정. class값이 c1인 요소들의 바탕색 변경
 btn2.addEventListener('click', function(){
    const div2 = document.getElementsByClassName('c1');  //htmlcollection타입. 배열이 아닌 집합인 객체임.
    for(let i of div2 ){
    i.style.backgroundColor = 'yellow';
    }
 });
 
 //#btn3요소 찾기
 const btn3 = document.querySelector('button#btn3');
 btn3.addEventListener('click', () =>{
    //태그 이름이 div인 모든 요소를 찾아서 바탕색 변경
    const div3 = document.getElementsByTagName('div');
    console.log(div3);
    for(const e of div3) {
        e.style.backgroundColor = 'green';
    }
 });
 
 //document.querySeletor(), document.querySeletorAll():
 //-css선택자 문법으로 아규먼트를 전달.
 // tagname, #id, .classname, tagname#id.classname
 // parent > child
 // ancestor descender
 // element:pseudo-seletor
 
 const btn4 = document.querySelector('button#btn4');
 btn4.addEventListener('click', function() {
    const div4 = document.querySelector('#id4');
    console.log(div4);
    div4.style.backgroundColor ='lightgray';
 });
 
 const btn5 = document.querySelector('button#btn5');
 btn5.addEventListener('click', () => {
    const div5 = document. querySelectorAll('div.c2');// querySelectorAll은 forEach() 사용가능
    console.log(div5); //Nodelist(배열과 비슷)
    
    for(let e of div5) {
        e.style.backgroundColor = 'dodgerbule';
    }
    div5.forEach((x) => x.style.backgroundColor = 'violet');
 });
 
 
 
 
 
 
 
 
 
 
 