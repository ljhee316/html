/**
 * post/modify.jsp에 포함
 */

//html DOM(Document Object Model) 컨텐트 로딩이 끝났을때 이벤트리스너. 
 document.addEventListener('DOMContentLoaded', () => {  /** 해당html에서만 사용할 메서드를 이용할때 DOMContentLoaded 사용. */
    //form 요소찾기
    const modifyForm = document.querySelector('form#modifyForm');
    
    //글번호, 제목, 내용요소 찾기
    const inputId = document.querySelector('input#id');
    
    const inputTitle = document.querySelector('input#title');
    
    const textareaContent = document.querySelector('textarea#content');
    
    //삭제, 업데이트버튼찾기
    const btnDelete = document.querySelector('button#btnDelete');
    const btnUpdate = document.querySelector('button#btnUpdate');
    
    //삭제버튼에 클릭이벤트리스너 설정
    btnDelete.addEventListener('click', () => {
        const result = confirm('정말 삭제할까요?');
       // console.log(result); 리턴값이 boolean타입. 
       if(result) {
            //삭제(GET 방식) 요청을 서버로 보내기.  -->localtion.href: url주소는 변경해줌. 페이지이동.
            location.href=`delete?id=${inputId.value}`;// js`${}`=문자형 템플릿.  (jsp에서 ${}은 EL)
       }
    });
    
    //업데이트버튼 클릭이벤트리스너 설정
    btnUpdate.addEventListener('click', () => {
        //제목과 내용이 비어있는지 체크.
        const title = inputTitle.value; //input 요소에 입력된 값.
        const content = textareaContent.value; //textarea요소에 입력된 값
        if(title === '' || content === '') {
            alert('제목과 내용은 반드시 입력해야합니다!');
            return; //함수종료
        }
        const result = confirm('변경된 내용을 저장할까요?');
        if(result) {
            modifyForm.method = 'post'; // 폼 제출방식 설정.  설정안하면 기본값을 get.
            modifyForm.action = 'update'; // 폼 제출 요청 주소(url)
            modifyForm.submit(); // 폼 제출(서버로 요청을 보냄)
        }        
    });
    
 });