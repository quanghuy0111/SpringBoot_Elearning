function loadCategory() {
    axios({
        url: 'http://localhost:8080/api/user/category/public',
        method: 'GET',
        headers: {
            Authorization: 'Bearer ' + localStorage.getItem("USER_TOKEN")
          }
    })
        .then(function (resp) {
            //  Lấy ra mảng role
            let arrCate = resp.data;
            // Tạo danh sách thẻ option
            let strCateDropMenu = "";
            let strFooterMenu = "";
            for (let cateDto of arrCate) {
                strCateDropMenu += ` <a class="dropdown-item" href="javascript:void(0)" onclick="getCourseByCategory(${cateDto.id})">
                <img src="http://localhost:8080/category/${cateDto.icon}"></i>
                <span>${cateDto.title}</span>
            </a>`;
            strFooterMenu += ` <div class="col-md-3">
            <a class="category" href="javascript:void(0)" onclick="getCourseByCategory(${cateDto.id})" style="color:inherit;text-decoration:none">
            <img src="http://localhost:8080/category/${cateDto.icon}"></i>
            <span>${cateDto.title}</span>
            </a>
        </div>`;
               
            }
            // Truy cập tới thẻ select có id là 'roleId'
            let cateDropMenuTag = document.getElementById('categoryMenu');
            let cateFooterMenuTag = document.getElementById('footerMenu');
            // Thay thế các thẻ option cũ bằng danh sách thẻ option mới
            cateDropMenuTag.innerHTML = strCateDropMenu;
            cateFooterMenuTag.innerHTML = strFooterMenu;
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
        url: 'http://localhost:8080/api/user/register/public',
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
        "password":password,
    }

    axios({
        url: 'http://localhost:8080/api/user/auth/public/login',
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
            getUserById();
            
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


            
            console.log(user);
        })
        .catch(function (err) {
            console.log(err);
            console.log(user);
            
        })
}

//window.addEventListener("beforeunload", () => localStorage.removeItem('USER_TOKEN'));


//Hàm hiển thị tất cả các khóa học
  function getAllCourses(){
    axios({
        url: 'http://localhost:8080/api/user/course/public',
        method: 'GET',
        headers: {
            Authorization: 'Bearer ' + localStorage.getItem("USER_TOKEN")
          }
    })
        .then(function (resp) {
            let arrCourses = resp.data;
            let stringCourseSaleOff = "";
            let stringCoursePopular = "";
            
            for(let courseDto of arrCourses){
                stringCourseSaleOff += `<div class="col-md-3">
               <div class="course">
                   <img src="http://localhost:8080/course/${courseDto.image}" />
                   <h6 class="course-title">${courseDto.title}</h6>
                   <small class="course-content">
                   ${courseDto.content}
                   </small>
                   <div class="course-price">
                       <span>${courseDto.price} đ</span>
                       <small>${courseDto.promotionPrice} đ</small>
                   </div>
                   <div class="seller-label">Sale 10%</div>
                   <div class="course-overlay">
                       <a href="details.html?id=${courseDto.id}">
                           <h6 class="course-title">
                           ${courseDto.title}
                           </h6>
                           <div class="course-author">
                               <b>Giảng viên</b>
                               <span class="mx-1"> | </span>
                               <b>Phạm Huy</b>
                           </div>
                           <div class="course-info">
                               <span><i class="fa fa-play-circle"></i> ${courseDto.lecturesCount} lectures</span>
                               <span class="mx-1"> | </span>
                               <span><i class="fa fa-clock-o"></i> ${courseDto.hourCount} hours</span>
                           </div>
                           <small class="course-content">
                               ${courseDto.content}
                           </small>
                       </a>
                       <a href="javascript:void(0)" class="btn btn-sm btn-danger text-white w-100" onclick="addUserCourse(${courseDto.id})">Add to cart</a>
                   </div>
               </div>
           </div>`;
           stringCoursePopular +=`
           <div class="col-md-2">
           <div class="course">
               <img src="http://localhost:8080/course/${courseDto.image}" />
               <h6 class="course-title">${courseDto.title}</h6>
               <small class="course-content">
                  ${courseDto.content}
               </small>
               <div class="course-price">
                   <span>${courseDto.price} đ</span>
               </div>
               <div class="course-overlay">
                   <a href="details.html?id=${courseDto.id}">
                       <h6 class="course-title">
                           ${courseDto.title}
                       </h6>
                       <div class="course-author">
                           <b>Giảng Viên</b>
                           <span class="mx-1"> | </span>
                           <b>Phạm Huy</b>
                       </div>
                       <div class="course-info">
                           <span><i class="fa fa-play-circle"></i> ${courseDto.lecturesCount} lectures</span>
                           <span class="mx-1"> | </span>
                           <span><i class="fa fa-clock-o"></i> ${courseDto.hourCount} hours</span>
                       </div>
                       <small class="course-content">
                       ${courseDto.content}
                       </small>
                   </a>
                   <a href="javascript:void(0)" class="btn btn-sm btn-danger text-white w-100" onclick="addUserCourse(${courseDto.id})">Add to cart</a>
               </div>
           </div>
           </div>`;
                

            }
            document.getElementById('listCourseSaleOff').innerHTML = stringCourseSaleOff;
            document.getElementById('listCoursePopular').innerHTML = stringCoursePopular;
            
            
        })
        .catch(function (err) {
            console.log(err.response);
            swal("Sorry", "Đăng nhập Thất Bại!", "error");

        })
  }



//Hàm mua khóa học
function addUserCourse(idCourse){
    let idUser = document.getElementById('idUser').value;
    let roleId = document.getElementById('roleId').value;
    let userCourse = {
        "user":{
            "id":idUser
        },
        "course":{
            "id":idCourse
        },
        "roleId": roleId  
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


//Hàm lấy khóa học theo category
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
            let stringCoursePopular = "";
            
            for(let courseDto of arrCourses){
                
           stringCoursePopular +=`
           <div class="col-md-2">
           <div class="course">
               <img src="http://localhost:8080/course/${courseDto.image}" />
               <h6 class="course-title">${courseDto.title}</h6>
               <small class="course-content">
                  ${courseDto.content}
               </small>
               <div class="course-price">
                   <span>${courseDto.price} đ</span>
               </div>
               <div class="course-overlay">
                   <a href="details.html">
                       <h6 class="course-title">
                           ${courseDto.title}
                       </h6>
                       <div class="course-author">
                           <b>Giảng Viên</b>
                           <span class="mx-1"> | </span>
                           <b>Phạm Huy</b>
                       </div>
                       <div class="course-info">
                           <span><i class="fa fa-play-circle"></i> ${courseDto.lecturesCount} lectures</span>
                           <span class="mx-1"> | </span>
                           <span><i class="fa fa-clock-o"></i> ${courseDto.hourCount} hours</span>
                       </div>
                       <small class="course-content">
                       ${courseDto.content}
                       </small>
                   </a>
                   <a href="javascript:void(0)" class="btn btn-sm btn-danger text-white w-100" onclick="addUserCourse(${courseDto.id})">Add to cart</a>
               </div>
           </div>
           </div>`;
                

            }
            
            document.getElementById('listCoursePopular').innerHTML = stringCoursePopular;
            document.getElementById('listCoursePopular').focus();
            
        })
        .catch(function (err) {
            console.log(err.response);
            swal("Sorry", "Mua Thất Bại!", "error");

        })
}

//Hàm hiển thị User hiện tại
function loadUser(){
    if(localStorage.getItem("USER_TOKEN") != null){
        getUserById();
    }
    
    
}

loadUser();
getAllCourses();
loadCategory();