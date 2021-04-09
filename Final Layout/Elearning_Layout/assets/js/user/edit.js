// Load danh sách Role
function loadRole() {
    axios({
        url: 'http://localhost:8080/api/admin/role',
        method: 'GET',
        headers: {
            Authorization: 'Bearer ' + localStorage.getItem("USER_TOKEN")
          }

    })
        .then(function (resp) {
            //Lấy ra mảng role
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
            roleIdTag.innerHTML = `<option value="3">STUDENT</option>`;
            console.log(err.response);
        })
}

//lấy tên file ảnh
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

//Upload ảnh
function saveAvatar() {
    let avatarInput = document.getElementById("avatar");
    // KIỂM TRA XEM CHỌN HÌNH CHƯA
    if (avatarInput.files.length === 0) {
        alert("Vui lòng chọn file!");
        return;
    }

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

// Lấy User theo ID






    

//Edit User
function editUser() {

    var flag = true;
    const queryString = window.location.search;
    const urlParams = new URLSearchParams(queryString);
    const idUser = urlParams.get('id');

    var emailInput = document.getElementById('email').value;
    var nameInput = document.getElementById('name').value;
    var passwordInput = document.getElementById('password').value;
    var confirm = document.getElementById('confirm').value;
    var roleId = document.getElementById('roleId').value;
    

    var slides = document.getElementsByClassName('form-control');
    var user = {
        "id": idUser,
        "roleId": roleId
    }

    user.avatar = getFileName();

    for (var i = 0; i < slides.length; i++) {
        if (slides.item(i).readOnly === false) {


            if (slides.item(i).getAttribute('id') === 'name') {
                
                if (nameInput.length == 0) {
                    flag = false;
                    document.getElementById('fullnameErr').innerHTML = 'Vui lòng nhập họ tên!';
                }
                else {
                    document.getElementById('fullnameErr').innerHTML = '';
                }

                user.fullname = nameInput;

            }


            else if (slides.item(i).getAttribute('id') === 'email') {
                
                if (emailInput.length == 0) {
                    flag = false;
                    document.getElementById('emailErr').innerHTML = 'Vui lòng nhập email!';
                }
               else if (/^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/.test(emailInput) === false) {
                    flag = false;
                   document.getElementById('emailErr').innerHTML = 'Email không đúng định dạng!';
                }
                else {
                   document.getElementById('emailErr').innerHTML = '';
                }


                user.email = emailInput;
            }

            else if (slides.item(i).getAttribute('id') === 'password') {
              
                if (passwordInput.length == 0) {
                    flag = false;
                    document.getElementById('passwordErr').innerHTML = 'Vui lòng nhập mật khẩu!';
                }
                else if (passwordInput.length < 6) {
                    flag = false;
                    document.getElementById('passwordErr').innerHTML = 'Mật khẩu ít nhất 6 ký tự!';
                }
                else {
                    document.getElementById('passwordErr').innerHTML = '';
                }

                
                if (confirm.length == 0) {
                    flag = false;
                    document.getElementById('confirmErr').innerHTML = 'Vui lòng nhập lại mật khẩu!';
                }
                else if (confirm !== passwordInput) {
                    flag = false;
                    document.getElementById('confirmErr').innerHTML = 'Nhập lại mật khẩu không khớp!';
                }
                else {
                    document.getElementById('confirmErr').innerHTML = '';
                }

                user.password = passwordInput;
            }

            else if (slides.item(i).getAttribute('id') === 'avatar') {
                user.avatar = getFileName();


            }
            
            else {
                user.roleId = roleId;
              
            }

        }
    }
    if (flag == true) {
        axios({
            url: `http://localhost:8080/api/admin/user/${idUser}`,
            method: 'PUT',
            data: user,
            headers: {
                Authorization: 'Bearer ' + localStorage.getItem("USER_TOKEN")
              }
        })
            .then(function (resp) {
                console.log('Thành công');
                swal("Good job!", "Edit Thành Công!", "success");
                console.log(user);
                disableEdit();
                //
                

                
                


                //
            })
            .catch(function (err) {
                console.log('lỗi xảy ra :' + console.error());
                swal("Sorry", "Edit Thất Bại!", "error");
            })
    }

}



// Enable các trường edit
function enableEdit(code) {
    if (code == 1) {
        document.getElementById('name').readOnly = false;
        document.getElementById('name').value = "";
    }
    if (code == 2) {
        document.getElementById('email').readOnly = false;
        document.getElementById('email').value = "";
    }
    if (code == 3) {
        document.getElementById('password').readOnly = false;
        document.getElementById('confirm').readOnly = false;
        document.getElementById('password').value = "";
        document.getElementById('confirm').value = "";
    }

    if (code == 4) {
        document.getElementById('avatar').readOnly = false;
        document.getElementById('avatar').value = "";
    }
    if (code == 5) {
        document.getElementById('roleId').disabled = false;
        
    }
}
function disableEdit(){
    document.getElementById('name').readOnly = true;
    document.getElementById('name').value = "";

    document.getElementById('email').readOnly = true;
    document.getElementById('email').value = "";

    document.getElementById('password').readOnly = true;
    document.getElementById('confirm').readOnly = true;
    document.getElementById('password').value = "";
    document.getElementById('confirm').value = "";

    document.getElementById('avatar').readOnly = true;
    document.getElementById('avatar').value = "";

    document.getElementById('roleId').disabled = true;
}


function logout(){
    localStorage.removeItem('USER_TOKEN');
    location.replace("/login.html");
}






loadRole();








