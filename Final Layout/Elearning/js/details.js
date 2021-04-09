const queryString = window.location.search;

const urlParams = new URLSearchParams(queryString);

const idCourse = urlParams.get('id');

function loadBanner(){
   

    axios({
        url: `http://localhost:8080/api/user/course/public/${idCourse}`,
        method: 'GET',
        headers: {
            Authorization: 'Bearer ' + localStorage.getItem("USER_TOKEN")
          }
    })
        .then(function (resp) {
            courseDto = resp.data;
            stringBanner = ` <h1>${courseDto.title}</h1>
            <h5>
                ${courseDto.content}
            </h5>
            <h6 class="mt-3">
                <span><i class="fa fa-user m-1"></i> Created by </span>
                <a href="#" class="text-white font-weight-bold mr-4">Lê Quang Song</a>
                <span><i class="fa fa-calendar-check-o mr-1"></i> Last updated 04/2019</span>
            </h6>
            <h6 class="mt-3">
                <span><i class="fa fa-play-circle mr-1"></i>${courseDto.lecturesCount} lectures</span>
                <span class="mx-1"> | </span>
                <span><i class="fa fa-clock-o mr-1"></i> ${courseDto.hourCount} hours</span>
                <span class="ml-2">with <b class="mx-1">568,171</b> students enrolled</span>
            </h6>`;
            
            document.getElementById('courseBanner').innerHTML = stringBanner;
            document.getElementById('courseDescription').innerHTML = courseDto.description;
            
        })
        .catch(function (err) {
            console.log(err.response);
            

        })
}

function loadTarget(){


    axios({
        url: `http://localhost:8080/api/user/target/public/${idCourse}`,
        method: 'GET',
        headers: {
            Authorization: 'Bearer ' + localStorage.getItem("USER_TOKEN")
          }
    })
        .then(function (resp) {
           let arrTarget = resp.data;
           let stringTarget = "";
           let stringTarget2 = "";
           let count = 0;

           for(let targetDto of arrTarget){
               stringTarget += `<li>
               <i class="fa fa-check"></i>
               <span>${targetDto.title}</span>
           </li>`;
                count++;
                if(count >= 4){
                    stringTarget2 += `<li>
                    <i class="fa fa-check"></i>
                    <span>${targetDto.title}</span>
                </li>`;
                }
           }
            document.getElementById('columnTarget1').innerHTML = stringTarget;
            document.getElementById('columnTarget2').innerHTML = stringTarget2;
        
        })
        .catch(function (err) {
            console.log(err.response);
            

        })
}

function loadVideo(){
    axios({
        url: `http://localhost:8080/api/user/video/public/${idCourse}`,
        method: 'GET',
        headers: {
            Authorization: 'Bearer ' + localStorage.getItem("USER_TOKEN")
          }
    })
        .then(function (resp) {
           let arrVideo = resp.data;
           let stringVideo = "";

           for(let videoDto of arrVideo) {
            stringVideo += ` <li>
            <a href="${videoDto.url}" class="btn-video" data-video-id="6xB-uXqbOqo">
                <span> <i class="fa fa-play-circle mr-1"></i>
                    ${videoDto.title}
                </span>
                <span>${videoDto.timeCount}</span>
            </a>
        </li>`;   
                
           }
            document.getElementById("list-content").innerHTML = stringVideo;
        })
        .catch(function (err) {
            console.log(err.response);
            

        })
}

//Hàm đăng ký
function register(){
    let flag = true;
    let name = document.getElementById('rgName').value;
    if(name.length == 0){
        flag = false;
        document.getElementById('rgNameError').innerHTML = 'Vui lòng không bỏ trống tên';
    }
    else{
        document.getElementById('rgNameError').innerHTML = '';
    }
    let email = document.getElementById('rgEmail').value;
    if(email.length == 0){
        flag = false;
        document.getElementById('rgEmailError').innerHTML = 'Vui lòng không bỏ trống email';
    }
    else{
        document.getElementById('rgEmailError').innerHTML = '';
    }
    let password = document.getElementById('rgPassword').value;
    let confirm = document.getElementById('rgConfirm').value;
    if(password.length == 0){
        flag = false;

    }
    else{
        
    }

    let userDto = {
        "email":email,
        "password":password,
        "fullname":name
    }

    axios({
        url: 'http://localhost:8080/api/register/public',
        method: 'POST',
        data : userDto,
        headers: {
            Authorization: 'Bearer ' + localStorage.getItem("USER_TOKEN")
          }
    })
        .then(function (resp) {
            swal("Good job!", "Đăng KýThành Công!", "success");
        })
        .catch(function (err) {
            console.log(err.response);
            swal("Sorry", "Thêm Thất Bại!", "error");

        })

}

//Hàm log in
function login(){
    let email = document.getElementById('lgEmail').value;
    let password = document.getElementById('lgPassword').value;

    let userDto = {
        "email":email,
        "password":password
    }

    axios({
        url: 'http://localhost:8080/api/auth/public/login',
        method: 'POST',
        data : userDto,
        headers: {
            Authorization: 'Bearer ' + localStorage.getItem("USER_TOKEN")
          }
    })
        .then(function (resp) {
            swal("Good job!", "Đăng nhập Thành Công!", "success");
    
            // B3. LƯU TOKEN VÀO MÁY NGƯỜI DÙNG
            localStorage.setItem('USER_TOKEN', resp.data);
            getUserById()
            
        })
        .catch(function (err) {
            console.log(err.response);
            
            swal("Sorry", "Đăng nhập Thất Bại!", "error");

        })

}


//Hàm hiển thị User sau khi đăng nhập thành công
function getUserById(){
    let signUpAndIn = document.getElementById('signUpAndIn');
  
    axios({
        url: `http://localhost:8080/api/user`,
        method: 'GET',
        headers: {
            Authorization: 'Bearer ' + localStorage.getItem("USER_TOKEN")
          }

    })
        .then(function (resp) {
            
            let user = resp.data;
            signUpAndIn.innerHTML = `<div class="dropdown">
            <div class="dropdown-toggle font-weight-bold text-dark" data-toggle="dropdown">
            <img src="http://localhost:8080/profile/${user.avatar}" class="img-radius"
            alt="User-Profile-Image">
            <span style=" cursor: pointer">${user.fullname}</span>
            </div>
            <input type="hidden" id="idUser" value = "${user.id}"></input>
            <input type="hidden" id="roleId" value = "${user.roleId}"></input>
            <div class="dropdown-menu dropdown-menu-right">
            <a class="dropdown-item" href="profile.html">Thông tin cá nhân</a>
            <a class="dropdown-item" href="course.html">Khóa học của tôi</a>
                
                <div class="dropdown-divider"></div>
                <a class="dropdown-item" href="javascript:void(0)" onclick="logout()">Đăng xuất</a>
            </div>
        </div>`


            
           
        })
        .catch(function (err) {
            console.log(err);
            
            
        })
}
//Hàm hiển thị User hiện tại
function loadUser(){
    if(localStorage.getItem("USER_TOKEN") != null){
        getUserById();
    }
    
    
}

//Hàm mua khóa học
function addUserCourse(){
    let idUser = document.getElementById('idUser').value;
    let roleId = document.getElementById('roleId').value;
    let userCourse = {
        "user":{
            "id":idUser
        },
        "course":{
            "id":idCourse
        },
        "roleId":roleId    
    }
    axios({
        url: 'http://localhost:8080/api/user/userCourse',
        method: 'POST',
        data : userCourse,
        headers: {
            Authorization: 'Bearer ' + localStorage.getItem("USER_TOKEN")
          }
    })
        .then(function (resp) {
            swal("Good job!", "Mua khóa học thành công!", "success");
    
            
        })
        .catch(function (err) {
            console.log(err.response);
            swal("Sorry", "Mua Thất Bại!", "error");

        })
}

//Hàm log out
function logout(){
    localStorage.removeItem('USER_TOKEN');
    signUpAndIn.innerHTML = `<button class="btn btn-outline-secondary" data-toggle="modal"
    data-target="#loginModal">Login</button>
<button class="btn btn-danger ml-2" data-toggle="modal" data-target="#signUpModal">Sign up</button>`;
    swal("Good job!", "Đăng xuất thành công", "success");  
}

function loadCourseBrief() {
    let courseBrief = document.getElementById("courseBrief");
    
    axios({
        url: `http://localhost:8080/api/user/course/public/${idCourse}`,
        method: 'GET',
        headers: {
            Authorization: 'Bearer ' + localStorage.getItem("USER_TOKEN")
          }
    })
        .then(function (resp) {
            let courseDto = resp.data;
            let stringCourseBrief = ` <h2 class="mb-4 font-weight-bold">
            ${courseDto.price} đ
            <small>${courseDto.promotionPrice} đ</small>
        </h2>
        <button type="button" class="btn btn-danger w-100" onclick="addUserCourse()">Buy now</button>
        
        <div class="course-buy-info mt-2">
            <span>This course includes</span>
            <small><i class="fa fa-play-circle-o"></i> ${courseDto.hourCount} hours on-demand video</small>
            <small><i class="fa fa-empire"></i> Full lifetime access</small>
            <small><i class="fa fa-tablet"></i> Access on mobile and TV</small>
            <small><i class="fa fa-recycle"></i> Certificate of Completion</small>
        </div>`
            courseBrief.innerHTML = stringCourseBrief;
            
        })
        .catch(function (err) {
            console.log(err.response);
            console.log(idCourse);
            console.log(courseDto);
            swal("Sorry", "Không lấy được courseBrief", "error");

        })
}



loadTarget();
loadBanner();
loadVideo();
loadUser();
loadCourseBrief();