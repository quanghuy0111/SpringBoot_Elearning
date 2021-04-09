//Hàm get ra list ROLE
function loadRole() {
    axios({
        url: 'http://localhost:8080/api/admin/role',
        method: 'GET',
        headers: {
            Authorization: 'Bearer ' + localStorage.getItem("USER_TOKEN")
          }
    })
        .then(function (resp) {
            //  Lấy ra mảng role
            let arrRole = resp.data;
            // Tạo danh sách thẻ option
            let strOption = "";
            for (let role of arrRole) {
                strOption += `<option value="${role.id}">${role.name}</option>`;
            }
            // Truy cập tới thẻ select có id là 'roleId'
            let roleIdTag = document.getElementById('roleId');
            // Thay thế các thẻ option cũ bằng danh sách thẻ option mới
            roleIdTag.innerHTML = strOption;

        })
        .catch(function (err) {
            console.log(err.response);
            document.getElementById('roleId').innerHTML = `<option value="3">STUDENT</option>`;
            //Nếu thất bại roleId mặc định sẽ là 3 , tức là
            

        })
}
//Gọi hàm load ra list ROLE
loadRole();




//Lấy ra file name từ máy local của user để lưu vào database
function getFileName() {
    var fullPath = document.getElementById('avatar').value;
    if (fullPath) {
        var startIndex = (fullPath.indexOf('\\') >= 0 ? fullPath.lastIndexOf('\\') : fullPath.lastIndexOf('/'));
        var filename = fullPath.substring(startIndex);
        if (filename.indexOf('\\') === 0 || filename.indexOf('/') === 0) {
            filename = filename.substring(1);
        }
        return filename;
    }
}

//Save hình ảnh xuống project
function saveAvatar() {
    let avatarInput = document.getElementById("avatar");
    // KIỂM TRA XEM CHỌN HÌNH CHƯA


    // ADD FILE VÀO ĐỐI TƯỢNG FORMDATA
    let formData = new FormData();
    formData.append('file', avatarInput.files[0]);

    axios({
        url: 'http://localhost:8080/api/admin/file/profile',
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


//Thêm mới 1 user
function addUser() {
    
    let flag = true;

    let name = document.getElementById('name').value;

    if (name.length == 0) {
        flag = false;
        document.getElementById('fullnameErr').innerHTML = 'Vui lòng nhập họ tên!';
    }
    else {
        document.getElementById('fullnameErr').innerHTML = '';
    }

    let email = document.getElementById('email').value;
    if (email.length == 0) {
        flag = false;
        document.getElementById('emailErr').innerHTML = 'Vui lòng nhập email!';
    }
    else if (/^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/.test(email) === false) {
        flag = false;
        document.getElementById('emailErr').innerHTML = 'Email không đúng định dạng!';
    }
    else {
        document.getElementById('emailErr').innerHTML = '';
    }

    let password = document.getElementById('password').value;
    if (password.length == 0) {
        flag = false;
        document.getElementById('passwordErr').innerHTML = 'Vui lòng nhập mật khẩu!';
    }
    else if (password.length < 6) {
        flag = false;
        document.getElementById('passwordErr').innerHTML = 'Mật khẩu ít nhất 6 ký tự!';
    }
    else {
        document.getElementById('passwordErr').innerHTML = '';
    }

    let confirm = document.getElementById('confirm').value;
    if (confirm.length == 0) {
        flag = false;
        document.getElementById('confirmErr').innerHTML = 'Vui lòng nhập lại mật khẩu!';
    }
    else if (confirm !== password) {
        flag = false;
        document.getElementById('confirmErr').innerHTML = 'Nhập lại mật khẩu không khớp!';
    }
    else {
        document.getElementById('confirmErr').innerHTML = '';
    }

    let avatarInput = document.getElementById('avatar');
    if (avatarInput.files.length === 0) {
        flag = false;
        alert('Vui lòng chọn file');
    }


    let roleId = document.getElementById('roleId').value;


    



    if (flag === true) {
        // TẠO ĐỐI TƯỢNG USER
        let user = {
            "fullname": name,
            "email": email,
            "password": password,
            "confirm": confirm,
            "avatar": getFileName(),
            "roleId": roleId,
            
        }
       

        // GỌI API THÊM MỚI
        axios({
            url: 'http://localhost:8080/api/admin/user',
            method: 'POST',
            data: user,
            headers: {
                Authorization: 'Bearer ' + localStorage.getItem("USER_TOKEN")
              }
        })
            .then(function (resp) {
                console.log('Thành công! ' + resp.data);
                swal("Good job!", "Thêm Mới Thành Công!", "success");
                saveAvatar();

            })
            .catch(function (err) {
                console.log(user);
                swal("Sorry", "Thêm Thất Bại!", "error");
                
            })
    }
}


//Hàm đăng xuất
function logout(){
    localStorage.removeItem('USER_TOKEN');
    location.replace("/login.html");
}



