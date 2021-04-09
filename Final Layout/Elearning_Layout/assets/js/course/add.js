
//Hàm load ra category
function loadCategory() {
    axios({
        url: 'http://localhost:8080/api/admin/category',
        method: 'GET',
        headers: {
            Authorization: 'Bearer ' + localStorage.getItem("USER_TOKEN")
          }
    })
        .then(function (resp) {
            //  Lấy ra mảng role
            let arrCate = resp.data;
            // Tạo danh sách thẻ option
            let strOption = "";
            for (let cateDto of arrCate) {
                strOption += `<option value="${cateDto.id}">${cateDto.title}</option>`;
            }
            // Truy cập tới thẻ select có id là 'roleId'
            let cateIdTag = document.getElementById('categoryId');
            // Thay thế các thẻ option cũ bằng danh sách thẻ option mới
            cateIdTag.innerHTML = strOption;
        })
        .catch(function (err) {
            console.log(err.response);
            

        })
}
loadCategory();

//Hàm lấy ra tên file từ máy local của user để save xuống database
function getFileName() {
    var fullPath = document.getElementById('image').value;
    if (fullPath) {
        var startIndex = (fullPath.indexOf('\\') >= 0 ? fullPath.lastIndexOf('\\') : fullPath.lastIndexOf('/'));
        var filename = fullPath.substring(startIndex);
        if (filename.indexOf('\\') === 0 || filename.indexOf('/') === 0) {
            filename = filename.substring(1);
        }
        return filename;
    }
}

//Hàm save image xuống project
function saveImage() {
    let imageInput = document.getElementById("image");
   


    // ADD FILE VÀO ĐỐI TƯỢNG FORMDATA
    let formData = new FormData();
    formData.append('file', imageInput.files[0]);

    axios({
        url: 'http://localhost:8080/api/admin/file/course',
        method: 'POST',
        data: formData,
        headers: {
            'Content-Type': 'multipart/form-data',
            Authorization: 'Bearer ' + localStorage.getItem("USER_TOKEN")

        }
    })
        .then(function (resp) {
            console.log(resp.data);
        })
        .catch(function (err) {
            console.log(err)
        })

}


var idUser = document.getElementById('userId').value;
var roleId = document.getElementById('roleId').value;
var lastCourseId = document.getElementById('lastCourseId').value;


//Hàm thêm một khóa học
function addCourse() {

    let flag = true;

    // let titleInput = document.getElementById('title').value;
    // let lecturesCountInput = document.getElementById('lecturesCount').value;
    // let hourCountInput = document.getElementById('hourCount').value;
     let cateInput= document.getElementById('categoryId').value;
    // let contentInput = document.getElementById('content').value;
    // let priceInput = document.getElementById('price').value;
    // let discountInput = document.getElementById('discount').value;
    // let descriptionInput = document.getElementById('description').value;
    
    let titleInput = document.getElementById('title').value;
    if (titleInput.length == 0) {
        flag = false;
        document.getElementById('titleError').innerHTML = 'Vui lòng nhập tiêu đề!';
    }
    else {
        document.getElementById('titleError').innerHTML = '';
    }

    let lecturesCountInput = document.getElementById('lecturesCount').value;
    if (lecturesCountInput == 0) {
        flag = false;
        document.getElementById('lecturesCountError').innerHTML = 'Vui lòng nhập số bài học!';
    }
   
    else {
        document.getElementById('lecturesCountError').innerHTML = '';
    }

    let hourCountInput = document.getElementById('hourCount').value;
    if (hourCountInput == 0) {
        flag = false;
        document.getElementById('hourCountError').innerHTML = 'Vui lòng nhập số giờ học!';
   
    }else {
        document.getElementById('hourCountError').innerHTML = '';
    }

    let contentInput = document.getElementById('content').value;
    if (contentInput.length == 0) {
        flag = false;
        document.getElementById('contentError').innerHTML = 'Vui lòng nhập nội dung!';
    }
   
    
    else {
        document.getElementById('contentError').innerHTML = '';
    }

    let imageInput = document.getElementById('image');
    if (imageInput.files.length === 0) {
        flag = false;
        document.getElementById('imageError').innerHTML = 'Vui lòng chọn hình ảnh';
    }
    else{
        document.getElementById('imageError').innerHTML = '';

    }
	
	let priceInput = document.getElementById('price').value;
	if (priceInput == 0) {
        flag = false;
        document.getElementById('priceError').innerHTML = 'vui lòng nhập giá';
    }
	else{
		
        document.getElementById('priceError').innerHTML = '';
	}
	
	let descriptionInput = document.getElementById('description').value;
	if (descriptionInput.length == 0) {
        flag = false;
        document.getElementById('descriptionError').innerHTML = 'vui lòng nhập mô tả';
    }
	else{
		
        document.getElementById('descriptionError').innerHTML = '';
	}
 

    let discountInput = document.getElementById('discount').value;
	if (discountInput == 0) {
        flag = false;
        document.getElementById('discountError').innerHTML = 'vui lòng nhập giảm giá';
    }
	else{
		
        document.getElementById('discountError').innerHTML = '';
	}



    
        

    if (flag === true) {
        // TẠO ĐỐI TƯỢNG USER
        let courseDto = {
            "title": titleInput,
            "image": getFileName(),
            "lecturesCount": lecturesCountInput,
            "price": priceInput,
            "hourCount": hourCountInput,
            "category": {
				"id":cateInput
			},
            "content": contentInput,
            "description": descriptionInput,
            "discount": discountInput,
            "lectureCount": lecturesCountInput
            
        }

     


            


                


        // GỌI API THÊM MỚI
        axios({
            url: 'http://localhost:8080/api/admin/course',
            method: 'POST',
            data: courseDto,
            headers: {
                Authorization: 'Bearer ' + localStorage.getItem("USER_TOKEN")
              }

        })
            .then(function (resp) {
                console.log('Thành công! ' + resp.data);
                swal("Good job!", "Thêm Mới Thành Công!", "success");
                saveImage();
                
                addUserCourse();

               

                
            
        



            })
            .catch(function (err) {
                console.log(courseDto);
                swal("Sorry", "Thêm Mới Thất Bại!", "error");
            })

    }
}

//Thêm user Course để biết được khóa học được thêm bởi user nào
function addUserCourse(){
    axios({
        url: 'http://localhost:8080/api/admin/user/current',
        method: 'GET',
        headers: {
            Authorization: 'Bearer ' + localStorage.getItem("USER_TOKEN")
          }

    })
        .then(function (resp) {
            userDto = resp.data;
            idUser = userDto.id;
            roleId = userDto.roleId;
            

        })
        .catch(function (err) {
            console.log(courseDto);
            swal("Sorry", "Thêm Mới Thất Bại!", "error");
        })






    axios({
        url: 'http://localhost:8080/api/admin/course/last',
        method: 'GET',
        headers: {
            Authorization: 'Bearer ' + localStorage.getItem("USER_TOKEN")
          }

    })
        .then(function (resp) {
            courseDto = resp.data;
            lastCourseId = courseDto.id;
         
            /////////Gọi Axios thêm
           
            let userCourse = {
                "user":{
                    "id":idUser
                },
                "course":{
                    "id":lastCourseId  
                },
                "roleId" : roleId
        }
        
        
    
        
        
        axios({
            url: 'http://localhost:8080/api/admin/userCourse',
            method: 'POST',
            data : userCourse,
            headers: {
                Authorization: 'Bearer ' + localStorage.getItem("USER_TOKEN")
              }
    
        })
            .then(function (resp) {
               console.log("Thêm user course thành công nhé")
                
    
            })
            .catch(function (err) {
                console.log();
                swal("Sorry", "Thêm Mới Thất Bại!", "error");
            })


            /////////
        })
        .catch(function (err) {
            console.log(courseDto);
            swal("Sorry", "Thêm Mới Thất Bại!", "error");
        })
}

//Hàm sign out
function logout(){
    localStorage.removeItem('USER_TOKEN');
    location.replace("/login.html");
}