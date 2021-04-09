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


function saveImage() {
    let imageInput = document.getElementById("image");
    // KIỂM TRA XEM CHỌN HÌNH CHƯA


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





function editCourse() {
    //Lấy ra tham số từ URL
    const queryString = window.location.search;
    const urlParams = new URLSearchParams(queryString);
    const idCourse = urlParams.get('id');


    let flag = true;

    let titleInput = document.getElementById('title').value;
    let lecturesCountInput = document.getElementById('lecturesCount').value;
    let hourCountInput = document.getElementById('hourCount').value;
    let cateInput= document.getElementById('categoryId').value;
    let contentInput = document.getElementById('content').value;
    let priceInput = document.getElementById('price').value;
    let discountInput = document.getElementById('discount').value;
    let descriptionInput = document.getElementById('description').value;
    

    if (flag === true) {
        // TẠO ĐỐI TƯỢNG USER
        let courseDto = {
            "id":idCourse,
            "title": titleInput,
            "image": getFileName(),
            "lecturesCount": lecturesCountInput,
            "price": priceInput,
            "hourCount": hourCountInput,
            "category": {
                "id":cateInput,
            },
            "content": contentInput,
            "description": descriptionInput,
            "discount": discountInput,
            "lectureCount": lecturesCountInput
            
        }


        // GỌI API THÊM MỚI
        axios({
            url: `http://localhost:8080/api/admin/course/${idCourse}`,
            method: 'PUT',
            data: courseDto,
            headers: {
                Authorization: 'Bearer ' + localStorage.getItem("USER_TOKEN")
              }

        })
            .then(function (resp) {
                console.log('Thành công! ' + resp.data);
                swal("Good job!", "Sửa Thành Công!", "success");
                saveImage();

            })
            .catch(function (err) {
                console.log(courseDto);
                swal("Sorry", "Thêm Mới Thất Bại!", "error");
            })
    
    }
}


function logout(){
    localStorage.removeItem('USER_TOKEN');
    location.replace("/login.html");
}

