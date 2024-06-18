/**
 * post/details.jsp에 포함
 */


document.addEventListener('DOMContentLoaded', () => {
    
    //btnToggleComment 버튼요소찾기
    const btnToggleComment = document.querySelector('button#btnToggleComment');  //$(buttom#btn  ->ajax에서 사용.)
    
    //collapseComments div 요소를 부트스트랩의 Collapse 객체로 생성.
    const bsCollapse = new bootstrap.Collapse('div#collapseComments', {toggle: false});//false  접혀있는상태.
    
    // 댓글 토글 버튼에 클릭 이벤트 리스너를 등록.
    btnToggleComment.addEventListener('click', () => {
        bsCollapse.toggle();//toggle 열었다 접어두기 하겠다.
        
        if (btnToggleComment.innerHTML === '댓글 보기') {
            btnToggleComment.innerHTML = '댓글 감추기';
            //post에 달려있는 모든 댓글 목록보여주기
            getAllComments();
            
        } else {
            btnToggleComment.innerHTML = '댓글 보기';
        }
    });
    
    
    //부트스크랩 모달(다이얼로그) 객체 생성.
    const commentModal = new bootstrap.Modal('div#commentModal', {backdrop: true});
    
    //모달의 저장 버튼을 찾고 클릭이벤트 리스너를 설정.
    const btnUpdateComment = document.querySelector('button#btnUpdateComment');
    btnUpdateComment.addEventListener('click', updateComment);
    
    
    
    /*----------------------------아래는 함수들-------------------------------------------------------*/
    
    //등록버튼 btnRegisterComment 요소를 찾기.
    const btnRegisterComment = document.querySelector('button#btnRegisterComment');
    
    //등록버튼 클릭이벤트 리스너 등록
    btnRegisterComment.addEventListener('click', registerComment);  //registerComment  함수임.
    
    //댓글등록 이벤트 리스너 콜백(함수) registerComment
    function registerComment() {

        //댓글이 달린 포스트번호 찾기
        const postId = document.querySelector('input#id').value;

        //댓글의 내용찾기
        const ctext = document.querySelector('textarea#ctext').value;
        
        //댓글의 작성자아이디 찾기
        const username = document.querySelector('input#username').value;
        
        //댓글내용, 댓글작성자가 비어 있는지 체크
        if (ctext === '' || username === '') {
            alert('댓글내용과 작성자는 반드시 입력하세요')
            return;
        } 
        
        //Ajax 요청에서 보낼 데이터 객체를 생성
       /* const data = { postId:  postId,
                       ctext: ctext,
                       username: username
                       };   아래 간단한버전 */ 
        const data = {postId, ctext, username};  //CommentCreateDto 필드와 이름이 같아야함.
        console.log(data);
        
        //post방식의 Ajax 요청을 보냄. 응답 성공과 실패에 대한 콜백 등록.
        axios   
        .post('../api/comment', data)   //현재주소 http://localhost:8673/spring2/post/details?id=42  post폴더위치임.
        .then((response)=>{    //성공했을때
            //console.log(response);
            console.log(response.data); //RestController 에서 보낸 응답 메세지.(return ResponseEntity.ok(result);)
            if(response.data === 1) { 
                alert('댓글 1개 등록 성공');
                document.querySelector('textarea#ctext').value = '';
                document.querySelector('input#username').value = '';
                //댓글목록 갱신
                getAllComments();
                
            }            
        }) 
        .catch((error)=>{     //실패했을때
            console.log(error);   
        }); 
    }
   
    

    //포스트에 달려있는 모든댓글 목록 가져오기
    function getAllComments(){
        
        //댓글목록을 요청하기위한 포스트 번호 찾기
        const postId = document.querySelector('input#id').value;
        
        //댓글목록요청하기위한 restAPI uri 선언
        const uri = `../api/comment/all/${postId}`;
        
        //Ajax요청보내기
        axios.get(uri)
             .then((response => {
                console.log(response.data);
                //댓글목록을 HTML로 작성-> details.jsp  div#comments 영역에 출력하기
                makeCommentElements(response.data);
                
             }))
             .catch((error => {
                console.log(error);
             }));
        
    }
    
    
    //댓글 목록(댓글 객체들의 배열)을 아규먼트로 전달받아서 HTML을 작성.
    function makeCommentElements(data){
        //댓글목록 HTML이 삽입될 div찾기
        const divComments = document.querySelector('div#comments');
        
        //댓글목록 HTML코드
        let htmlStr = '';
        for (let comment of data) {
            //댓글 최종수정시간
            const modifiedTime = new Date(comment.modifiedTime).toLocaleString();  //시간을 문자열로 변환해줌.  Date()->자바함수, ->TimeStamp타입이어야함.
            
            htmlStr += `
            <div class="card card-body my-1">
                <div style="font-size: 0.825rem;">
                    <span>${comment.id}</span>
                    <span class="fw-bold">${comment.username}</span>
                    <span class="text-secondary">${modifiedTime}</span>
                </div>
                <div>${comment.ctext}</div>
                <div>
                    <button class="btnDeleteComment btn btn-outline-danger btn-sm"
                            data-id="${comment.id}">삭제</button>
                    <button class="modifyComment btn btn-outline-primary btn-sm"
                            data-id="${comment.id}">수정</button>              
                </div>
            </div>
            `;  //id부여하지않은이유: 갯수가 확실치 않아서. 각각 따로가 아닌 한꺼번에 찾기위해  class만 선언함. data-id: 커스텀태그속성. 
        }
        //작성된 HTML코드를 div영역에 삽입함.
        divComments.innerHTML = htmlStr;
        
        //모든 삭제버튼찾아서 클릭이벤트리스너 설정.  버튼이 생성된후 이벤트리스너 해줘야함.
        const btnDeletes = document.querySelectorAll('button.btnDeleteComment');
        for(let btn of btnDeletes) {
            btn.addEventListener('click', deleteComment);
        }
    
        //모든 수정버튼찾아서 클릭이벤트리스너 설정.
        const btnModifies = document.querySelectorAll('button.modifyComment');
        for(let btn of btnModifies) {
            btn.addEventListener('click', showCommentModal);
        }
    }
    
    function deleteComment(event) {
        //이벤트리스너 콜백의 아규먼트 event객체는 target(이벤트가 발생한 요소) 속성을 가지고 있음.  target:button.btnDeleteComment.btn.btn-outline-danger.btn-sm
        console.log(event.target);
        const id = event.target.getAttribute('data-id');  //HTML 요소의 속성값 찾기. --> data-id(커스텀태그속성. 대쉬로 표현) 삭제할 아이디번호찾음.
        
        //삭제여부확인
        const result = confirm('댓글을 정말 삭제할까요?');
        if(!result) {  //취소했을때
            return;
        }
        
        //Ajax로 삭제요청을 보낼 REST API URI만들기
        const uri = `../api/comment/${id}`;
        
        //Ajax요청보내기
        axios
        .delete(uri)
        .then((response) => {
            console.log(response.data);
            if (response.data === 1) {
                alert(`댓글${id} 삭제 성공`);
                getAllComments(); //댓글목록 갱신.
            }
        })
        .catch((error) => {
            console.log(error);
        });
    }
    
    function showCommentModal(event) { 
        //이벤트 타겟(수정버튼)의 data-id 속성값을 읽기
        const id = event.target.getAttribute('data-id');
        //Ajax요청을 보내서 댓글 아이디로 검색하기
        const uri = `../api/comment/${id}`;
        axios
        .get(uri)
        .then((response) => {
            console.log(response.data);  //response 는 data(객체) 속성을 항상 가지고있음.
            //const commnetId = response.data.id;
            //모달의 댓글번호, 댓글내용찾아서 value 채우기
            id = document.querySelector('input#modalCommentId').value;
            response.data.ctext = document.querySelector('textarea#modalCommentText').value;
        })
        .catch((error) => console.log(error));
    }
    
    //댓글 업데이트 모달의 저장 버튼의 클릭 이벤트 리스너
    function updateComment() {
        //업데이트할 id변경
        const id = document.querySelector('input#modalCommentId').value;
        
        //업데이트할 댓글내용변경
        const ctext = document.querySelector('textarea#modalCommentText').value;
        if(ctext ==='') {
            alert('업데이트 할 내용을 입력하세요.')
            retrun; //이벤트리스너종료
        }
        
        //댓글 업데이트 요청 uri
        const uri = `../api/comment/${id}`;
        
        //Ajax요청보내기
        axios
        .put(uri, {ctext})    // {ctext} = {ctext : ctext}
        .then((response)=>{
            console.log(response);
            
            //댓글목록 갱신
            getAllComments();
            
            //모달(다이얼로그) 숨기기
            commentModal.hide();
            
        })
        .catch((error)=> console.log(error));
        
    }
    
    
    
    
});