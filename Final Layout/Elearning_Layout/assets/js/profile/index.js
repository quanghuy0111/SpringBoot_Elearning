
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
    .then(function(resp){
        let image = resp.data;
        // TRUY CẬP TỚI THẺ IMG
        let imgAvatar = document.getElementById('imgAvatar');
        // THAY ĐỔI GIÁ TRI CỦA THẺ SRC
        imgAvatar.setAttribute('src', `http://localhost:8080/profile/${image}`);
    })
    .catch(function(err){
        console.log(err)
    })
    
}

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


function editProfileOther(){
        
        
        axios({
            url: 'http://localhost:8080/api/admin/user/current',
            method: 'GET',
            headers: {
                Authorization: 'Bearer ' + localStorage.getItem("USER_TOKEN")
              }
    
        })
            .then(function (resp) {
                let userDto = resp.data;
                let idUser = userDto.id;
                let roleId = userDto.roleId;
                editOther(idUser);
                
    
            })
            .catch(function (err) {
                console.log(err);
              
            })
         
         
    
}
function editProfileImage(){
        
        
    axios({
        url: 'http://localhost:8080/api/admin/user/current',
        method: 'GET',
        headers: {
            Authorization: 'Bearer ' + localStorage.getItem("USER_TOKEN")
          }

    })
        .then(function (resp) {
            let userDto = resp.data;
            let idUser = userDto.id;
            let roleId = userDto.roleId;
            editImage(idUser);
            saveAvatar();
            

        })
        .catch(function (err) {
            console.log(err);
          
        })
     
     

}

function editProfilePassword(){
        
        
    axios({
        url: 'http://localhost:8080/api/admin/user/current',
        method: 'GET',
        headers: {
            Authorization: 'Bearer ' + localStorage.getItem("USER_TOKEN")
          }

    })
        .then(function (resp) {
            let userDto = resp.data;
            let idUser = userDto.id;
            editPassword(idUser);

            

        })
        .catch(function (err) {
            console.log(err);
          
        })
     
     

}




function editOther(id){
    var fullnameInput = document.getElementById('fullname').value;
    var addressInput = document.getElementById('address').value;
    var phoneInput = document.getElementById('phone').value;

    var user = {
        "id":id,
        "fullname": fullnameInput,
        "address":addressInput,
        "phone":phoneInput
        
    }

    axios({
        url: `http://localhost:8080/api/admin/user/profile`,
        method: 'PUT',
        data: user,
        headers: {
            Authorization: 'Bearer ' + localStorage.getItem("USER_TOKEN")
          }
    })
        .then(function (resp) {
            console.log('Thành công');
            swal("Good job!", "Thêm Mới Thành Công!", "success");
        
        })
        .catch(function (err) {
            console.log('lỗi xảy ra :' + console.error());
            
        })




}

function editImage(id){
    
    

    var user = {
        "id":id,
        "avatar":getFileName()
        
    }

    axios({
        url: `http://localhost:8080/api/admin/user/profile`,
        method: 'PUT',
        data: user,
        headers: {
            Authorization: 'Bearer ' + localStorage.getItem("USER_TOKEN")
          }
    })
        .then(function (resp) {
            console.log('Thành công');
            swal("Good job!", "Thêm Mới Thành Công!", "success");
            console.log(user);
        
        })
        .catch(function (err) {
            console.log('lỗi xảy ra :' + console.error());
            
        })

}

function editPassword(id){
    
    var password = document.getElementById('password').value;

    var user = {
        "id":id,
        "password":password
        
    }

    axios({
        url: `http://localhost:8080/api/admin/user/profile`,
        method: 'PUT',
        data: user,
        headers: {
            Authorization: 'Bearer ' + localStorage.getItem("USER_TOKEN")
          }
    })
        .then(function (resp) {
            console.log('Thành công');
            swal("Good job!", "Thêm Mới Thành Công!", "success");
        
        })
        .catch(function (err) {
            console.log('lỗi xảy ra :' + console.error());
            
        })

}

function showProfileName(){
    
    axios({
        url: 'http://localhost:8080/api/admin/user/current',
        method: 'GET',
        headers: {
            Authorization: 'Bearer ' + localStorage.getItem("USER_TOKEN")
          }

    })
        .then(function (resp) {
            let userDto = resp.data;
            let idUser = userDto.id;
            let roleId = userDto.roleId;
            getUserById(idUser);
            
            

        })
        .catch(function (err) {
            console.log(err);
          
        })
}

function getUserById(id){
    axios({
        url: `http://localhost:8080/api/admin/user/${id}`,
        method: 'GET',
        headers: {
            Authorization: 'Bearer ' + localStorage.getItem("USER_TOKEN")
          }

    })
        .then(function (resp) {
            let userDto = resp.data;
            
            document.getElementById('profileFullname').innerHTML = userDto.fullname;
            document.getElementById('profileEmail').innerHTML = userDto.email;
            document.getElementById('emailProfile').value = userDto.email;
            
            
            

        })
        .catch(function (err) {
            console.log(err);
          
        })
}

showProfileName();

