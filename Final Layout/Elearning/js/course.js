
function getUserById(){
    let currentUser = document.getElementById('currentUser');
    let dropMenu = document.getElementById('currentUserDropMenu');
    let userBanner = document.getElementById('userBanner');
    axios({
        url: `http://localhost:8080/api/user`,
        method: 'GET',
        headers: {
            Authorization: 'Bearer ' + localStorage.getItem("USER_TOKEN")
          }

    })
        .then(function (resp) {
            
            let user = resp.data;
            currentUser.innerHTML = `<div class="dropdown">
            <img src="http://localhost:8080/profile/${user.avatar}" class="img-radius"
            alt="User-Profile-Image">
            <span">${user.fullname}</span>`;

            dropMenu.innerHTML = `<a class="dropdown-item" href="profile.html">Thông tin cá nhân</a>
            <a class="dropdown-item" href="course.html">Khóa học của tôi</a>
            <div class="dropdown-divider"></div>
            <a class="dropdown-item" href="javascript:void(0)" onclick="logout()">Đăng xuất</a>`;
           
            userBanner.innerHTML = ` <h1>My course</h1>
            <h5>${user.fullname}</h5>`;

            
           
        })
        .catch(function (err) {
            console.log(err);
            
            
        })
}


function getAllCourses(){
    axios({
        url: 'http://localhost:8080/api/user/course/details',
        method: 'GET',
        headers: {
            Authorization: 'Bearer ' + localStorage.getItem("USER_TOKEN")
          }
    })
        .then(function (resp) {
            let arrCourses = resp.data;
            let stringCourse = "";
           
            
            for(let courseDto of arrCourses){
                stringCourse += ` <div class="col-md-3">
                <a href="details.html?id=${courseDto.id}" class="my-course-item">
                    <img src="http://localhost:8080/course/${courseDto.image}" alt="Picture">
                    <h6 class="my-course-title">${courseDto.title} </h6>
                    <div class="my-course-desc">
                       ${courseDto.description}
                    </div>
                    <div class="my-course-author">
                        <h6>
                            <small>Phạm Huy</small>
                            <small>Start course</small> 
                        </h6>
                    </div>
                </a>
            </div>`;
                

            }
            document.getElementById('listCourse').innerHTML = stringCourse;
           
            
            
        })
        .catch(function (err) {
            console.log(err.response);
            swal("Sorry", "Đăng nhập Thất Bại!", "error");

        })
  }

  function getCourseByCategory(id){
   
    axios({
        url: `http://localhost:8080/api/user/course/public/category/${id}`,
        method: 'GET',
        
        headers: {
            Authorization: 'Bearer ' + localStorage.getItem("USER_TOKEN")
          }
    })
        .then(function (resp) {
            let arrCourses = resp.data;
            let stringCourse = "";
            
            for(let courseDto of arrCourses){
                
                stringCourse += ` <div class="col-md-3">
                <a href="#" class="my-course-item">
                    <img src="http://localhost:8080/course/${courseDto.image}" alt="Picture">
                    <h6 class="my-course-title">${courseDto.title} </h6>
                    <div class="my-course-desc">
                       ${courseDto.description}
                    </div>
                    <div class="my-course-author">
                        <h6>
                            <small>Phạm Huy</small>
                            <small>Start course</small>
                        </h6>
                    </div>
                </a>
            </div>`;
                

            }
            
            document.getElementById('listCourse').innerHTML = stringCourse;
           
            
        })
        .catch(function (err) {
            console.log(err.response);
            swal("Sorry", "Mua Thất Bại!", "error");

        })
}

function loadUser(){
    if(localStorage.getItem("USER_TOKEN") != null){
        getUserById();
    }else{
        location.replace("/index.html");
    }
    
    
}

function logout(){
    localStorage.removeItem('USER_TOKEN');
    location.replace("/index.html"); 
}


loadUser();
getAllCourses();
getUserById();