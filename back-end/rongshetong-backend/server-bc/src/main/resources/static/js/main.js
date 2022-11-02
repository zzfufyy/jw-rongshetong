$(window).load(function() {
    var navTop = $('.nav-box').offset().top;
    $(window).resize(function() {
        var navTop = $('.nav-box').offset().top;
    });
    $(window).scroll(function() {
        if($(window).scrollTop() > navTop) {
            $('#nav').addClass('fixed');
        }else {
            $('#nav').removeClass('fixed');
        }
    });
    $('.nav li:not(:last-of-type)').click(function() {
        $(this).addClass('active').siblings().removeClass('active');
    });
    $(window).scroll(function(){
        if($(window).scrollTop()>=$(window).height()){
            $('.to_top').show();
        }else{
            $('.to_top').hide();
        }
    });
    $('.to_top').click(function() {
        $("html, body").animate({ scrollTop: 0 }, 120);
    });
    /*$('.nav a[href*=#]').each(function() {
        $($(this).attr('href')).data('position',$($(this).attr('href')).offset().top);
    });*/
    $(document).on("click", "#nav a[href*=#]:not([href=#])", function (event) {
        var hash = this.hash;
        var $target = $(hash);
        var scrolltop = $target.offset().top-$('.nav').outerHeight();
        event.preventDefault();
        if ($target.length) {
            $("html,body").stop().animate({
                scrollTop: scrolltop
            }, 300, function(){
                if(history.pushState) {
                    history.pushState(null, null, hash);
                }
            });
        }
    });
    $('#phone').keyup(function() {
        var pattern = /^(010|02[0-9]|0[3-9][0-9]{2})$/;
        if($(this).val().length<5&&pattern.test($(this).val())) {
           $(this).val($(this).val()+'-')
        }
    });
    $('body').scrollspy({
        target: '#nav',
        offset: $('.nav').outerHeight()+1
    })
});
if($(window).width()<768) {
    $('.top-phone a, .contact a').attr('href','tel:4006961100');
}
$('[name=mobile]').keyup(function() {
    if($(this).val().length == 11) {
        $(this).parents('.form-validate').validate().element($(this));
    }
})
$(".form-validate").each(function () {
    var myform = $(this);
    var myValidate = myform.validate({
        errorElement: 'span', //default input error message container
        errorClass: 'msg-error', // default input error message class
        focusInvalid: false, // do not focus the last invalid input
        ignore: "", // validate all fields including form hidden input
        rules: {
            company: {
                required: true,
                minlength: 4
            },
            license_number: {
                required: true,
                minlength: 8
            },
            certificate_address: {
                required: true
            },
            mailing_address: {
                required: true
            },
            phone: {
                phone: true
            },
            site_manager: {
                required: true,
                minlength: 2
            },
            card_number: {
                required: true,
                idCard: true
            },
            email: {
                required: true,
                email: true
            },
            mobile: {
                required: true,
                mobile: true
            },
            domain_name: {
                domain: true
            }
        },
        messages: {
            company: {
                required: "不能为空",
                minlength: "公司名称最少4位"
            },
            license_number: {
                required: "不能为空",
                minlength: "单位证件号码最少8位"
            },
            certificate_address: {
                required: "不能为空"
            },
            mailing_address: {
                required: "不能为空"
            },
            phone: {
                phone: "电话号码格式不正确"
            },
            site_manager: {
                required: "不能为空",
                minlength: "负责人姓名最少2位"
            },
            card_number: {
                required: "不能为空",
                idCard: "请输入正确的身份证号码"
            },
            email: {
                required: "不能为空",
                email: "请输入正确的电子邮箱"
            },
            mobile: {
                required: "不能为空",
                mobile: "手机号格式不正确"
            },
            domain_name: {
                domain: "域名格式不正确"
            }
        },
        errorPlacement: function (error, element) { // render error placement for each input type
            var icon = $(element).closest('.input').find("i.icon");
            icon.removeClass('icon-duihao').addClass("icon-tanhao");
            icon.attr("data-placement", "left");
            icon.attr("data-original-title", error.text()).tooltip({'container': 'body'});
            icon.tooltip('show');
            setTimeout(function () {
                icon.tooltip('hide');
            }, 2000);
            if ($(element).attr("id") == "mobile") {
                $("#getcode").prop("disabled", true);
            }
            // for other inputs, just perform default behavior
        },

        invalidHandler: function (event, validator) { //display error alert on form submit
            myform.data("validate", false);
        },
        highlight: function (element) { // hightlight error inputs
            $(element).closest('.input').removeClass("has-success").addClass('has-error'); // set error class to the control group
        },

        unhighlight: function (element) { // revert the change done by hightlight
            $(element).closest('.input').removeClass('has-error'); // set error class to the control group
        },

        success: function (label, element) {
            $(element).closest('.input').removeClass('has-error').addClass("has-success"); // set success class to the control group
            var icon = $(element).closest('.input').find("i.icon");
            icon.removeClass("icon-tanhao").addClass("icon-duihao");
            if ($(element).attr("id") == "mobile" && $("#getcode").text() == "获取验证码") {
                $("#getcode").prop("disabled", false);
            }
        },
        submitHandler: function (form) {
            myform.data("validate", true);
            if (!myform.data("ajax")) {
                form[0].submit(); // submit the form
            }
        }
    })
});
jQuery.validator.addMethod("mobile",function(value, element) {
        var length = value.length;
        var mobile = /^(13[0-9]{9})|(18[0-9]{9})|(14[0-9]{9})|(17[0-9]{9})|(15[0-9]{9})$/;
        return this.optional(element) || (length == 11 && mobile.test(value))
    }, "请正确填写您的手机号码");
jQuery.validator.addMethod("phone",function(value, element) {
    var length = value.length;
    var phone = /^(010|02[0-9]|0[3-9][0-9]{2})-\d{7,8}$/;
    return this.optional(element) || (phone.test(value))
}, "请正确填写您的电话号码");
jQuery.validator.addMethod("domain",function(value, element) {
    var domain = /^(https:|http:)?(\/\/)?((([a-z0-9]|[\u4e00-\u9fa5])+[\.|\-|\_]?([a-z0-9]|[\u4e00-\u9fa5])+)+)\.([a-z]|[\u4e00-\u9fa5]|\/)+\/?$/i;
    return this.optional(element) || (domain.test(value))
});
jQuery.validator.addMethod("idCard",function(value, element) {
    var iSum=0 ;
     var info="" ;
     aCity = ['11','12','13','14','15','21','22',
        '23','31','32','33','34','35','36',
        '37','41','42','43','44','45','46',
        '50','51','52','53','54','61','62',
        '63','64','65','71','81','82','91'];
     if(!/^\d{17}(\d|x)$/i.test(value)) return false;
     value=value.replace(/x$/i,"a");
     if(!$.inArray(parseInt(value.substr(0,2)),aCity)) return false;
     sBirthday=value.substr(6,4)+"-"+Number(value.substr(10,2))+"-"+Number(value.substr(12,2));
     var d=new Date(sBirthday.replace(/-/g,"/")) ;
     if(sBirthday!=(d.getFullYear()+"-"+ (d.getMonth()+1) + "-" + d.getDate()))return "身份证上的出生日期非法";
     for(var i = 17;i>=0;i --) iSum += (Math.pow(2,i) % 11) * parseInt(value.charAt(17 - i),11) ;
     if(iSum%11!=1) return false;
     return true;
}, "请输入正确的身份号");
jQuery.validator.addMethod("email",function(value, element) {
    var email = /^((([0-9]{5,11})|([a-zA-Z]\w{2,17}))@qq.com)|(\w+@[^@qq]+)$/;
    return this.optional(element) || (email.test(value))
}, "请输入正确的电子邮箱");