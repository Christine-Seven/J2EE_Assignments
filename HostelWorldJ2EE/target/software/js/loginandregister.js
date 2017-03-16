function toRegister() {
    var vipName=document.getElementById("vipName").value;
    var passwd=document.getElementById("pwd").value;
    var confirmpwd=document.getElementById("cpwd").value;
    var bankCardId=document.getElementById("bankCardId").value;
    if(vipName==""){
        alert("用户名不能为空");
        return false;
    }
    if(passwd==""){
        alert("密码不能为空");
        return false;
    }
    if(confirmpwd==""){
        alert("重复密码不能为空");
        return false;
    }
    if(bankCardId==""){
        alert("银行卡号不能为空");
        return false;
    }
    if(passwd!=confirmpwd) {
        alert("密码不一致");
        return false;
    }
    return true;
}

function toLogin() {
    var name=document.getElementById("name").value;
    var passwd=document.getElementById("password").value;
    if(name==""){
        alert("用户名不能为空");
        return false;
    }
    if(passwd=""){
        alert("密码不能为空");
        return false;
    }
    return true;
}