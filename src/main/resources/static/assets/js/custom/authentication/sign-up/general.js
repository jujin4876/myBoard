/******/ (() => { // webpackBootstrap
/******/ 	"use strict";
var __webpack_exports__ = {};
/*!*******************************************************************************************!*\
  !*** ../../../themes/metronic/html/demo8/src/js/custom/authentication/sign-up/general.js ***!
  \*******************************************************************************************/


// Class definition
var KTSignupGeneral = function() {
    // Elements
    var form;
    var submitButton;
    var validator;
    var passwordMeter;
    var idCheckButton;
    var idCheck;

    // Handle form
    var handleForm  = function(e) {
        // Init form validation rules. For more info check the FormValidation plugin's official documentation:https://formvalidation.io/
        validator = FormValidation.formValidation(
			form,
			{
				fields: {
					'Name': {
						validators: {
							notEmpty: {
								message: 'Name is required'
							}
						}
                    },
                    'username': {
						validators: {
							notEmpty: {
								message: 'ID is required'
							}
						}
					},
					'email': {
                        validators: {
							notEmpty: {
								message: 'Email address is required'
							},
                            emailAddress: {
								message: 'The value is not a valid email address'
							}
						}
					},
                    'Password': {
                        validators: {
                            notEmpty: {
                                message: 'The password is required'
                            },
                            callback: {
                                message: 'Please enter valid password',
                                callback: function(input) {
                                    if (input.value.length > 0) {
                                        return validatePassword();
                                    }
                                }
                            }
                        }
                    },
                    'confirm_password': {
                        validators: {
                            notEmpty: {
                                message: 'The password confirmation is required'
                            },
                            identical: {
                                compare: function() {
                                    return form.querySelector('[name="Password"]').value;
                                },
                                message: 'The password and its confirm are not the same'
                            }
                        }
                    },
                    /*'toc': {
                        validators: {
                            notEmpty: {
                                message: 'You must accept the terms and conditions'
                            }
                        }
                    }*/
				},
				plugins: {
					trigger: new FormValidation.plugins.Trigger({
                        event: {
                            password: false
                        }  
                    }),
					bootstrap: new FormValidation.plugins.Bootstrap5({
                        rowSelector: '.fv-row',
                        eleInvalidClass: '',
                        eleValidClass: ''
                    })
				}
			}
		);

        // Handle form submit
        submitButton.addEventListener('click', function (e) {
            e.preventDefault();

            validator.revalidateField('Password');

            const params = {
                username : form.username.value,
                password : form.Password.value,
                name : form.Name.value,
                email : form.email.value,
            }

            //const id = /*[[ ${id} ]]*/;
            //const uri = ( id ) ? `/api/members/${id}` : '/api/members';
            //const method = ( id ) ? 'PATCH' : 'POST';
            const uri = '/api/members';
            const method = 'POST';


            validator.validate().then(function(status) {
                if(idCheck){
                    if (status == 'Valid') {
                        // Show loading indication
                        submitButton.setAttribute('data-kt-indicator', 'on');

                        // Disable button to avoid multiple click
                        submitButton.disabled = true;

                        // Simulate ajax request
                        setTimeout(function() {
                            // Hide loading indication
                            submitButton.removeAttribute('data-kt-indicator');

                            // Enable button
                            submitButton.disabled = false;
                            fetch(uri, {
                                method: method,
                                headers: { 'Content-Type': 'application/json' },
                                body: JSON.stringify(params)

                                }).then(response => {
                                    if (!response.ok) {
                                    	throw new Error('Request failed...');
                                    }
                                    // Show message popup. For more info check the plugin's official documentation: https://sweetalert2.github.io/
                                    Swal.fire({
                                        text: "You have successfully reset your password!",
                                        icon: "success",
                                        buttonsStyling: false,
                                        confirmButtonText: "Ok, got it!",
                                        customClass: {
                                            confirmButton: "btn btn-primary"
                                        }
                                    }).then(function (result) {
                                        if (result.isConfirmed) {
                                           location.href = '/';
                                        }
                                    });

                                }).catch(error => {
                                    Swal.fire({
                                        text: "Sorry, looks like there are some errors detected, please try again.",
                                        icon: "error",
                                        buttonsStyling: false,
                                        confirmButtonText: "Ok, got it!",
                                        customClass: {
                                            confirmButton: "btn btn-primary"
                                        }
                                    });
                                });


                        }, 1500);
                    } else {
                        // Show error popup. For more info check the plugin's official documentation: https://sweetalert2.github.io/
                        Swal.fire({
                            text: "Sorry, looks like there are some errors detected, please try again.",
                            icon: "error",
                            buttonsStyling: false,
                            confirmButtonText: "Ok, got it!",
                            customClass: {
                                confirmButton: "btn btn-primary"
                            }
                        });
                    }
                }
                else{
                    Swal.fire({
                         text: "중복체크를 해주세여",
                         icon: "error",
                         buttonsStyling: false,
                         confirmButtonText: "Ok, got it!",
                         customClass: {
                            confirmButton: "btn btn-primary"
                         }
                     })
                }
		    });
        });

        // Handle password input
        form.querySelector('input[name="Password"]').addEventListener('input', function() {
            if (this.value.length > 0) {
                validator.updateFieldStatus('Password', 'NotValidated');
            }
        });

        idCheckButton.addEventListener('click', function (e){
            var id = form.querySelector('[name="username"]').value;
            if(id.length > 0){
                $.ajax({
                    url: '/login/idCheck',
                    type:'post',
                     data:{id:id},
                     success:function(result){
                        if(result){
                            idCheck = result;
                            Swal.fire({
                                text: "사용 가능한 아이디입니다.",
                                icon: "success",
                                buttonsStyling: false,
                                confirmButtonText: "Ok, got it!",
                                customClass: {
                                    confirmButton: "btn btn-primary"
                                }
                            })
                        }
                        else{
                            Swal.fire({
                                text: "중복 된 아이디입니다.",
                                icon: "error",
                                buttonsStyling: false,
                                confirmButtonText: "Ok, got it!",
                                customClass: {
                                    confirmButton: "btn btn-primary"
                                }
                            })

                        }
                     },
                     error:function(){
                        alert('실패')
                     }
                })
            }
            else{
                Swal.fire({
                    text: "아이디를 입력해주세여",
                    icon: "error",
                    buttonsStyling: false,
                    confirmButtonText: "Ok, got it!",
                    customClass: {
                        confirmButton: "btn btn-primary"
                    }
                })
            }
        })

    }

    // Password input validation
    var validatePassword = function() {
        return  (passwordMeter.getScore() === 100);
    }

    // Public functions
    return {
        // Initialization
        init: function() {
            // Elements
            form = document.querySelector('#kt_sign_up_form');
            submitButton = document.querySelector('#kt_sign_up_submit');
            passwordMeter = KTPasswordMeter.getInstance(form.querySelector('[data-kt-password-meter="true"]'));
            idCheckButton = document.querySelector('#id_check');
            //idCheck = idCheckButton.hidden == true ? true : false;
            idCheck= true;

            handleForm ();
        }
    };
}();

// On document ready
KTUtil.onDOMContentLoaded(function() {
    KTSignupGeneral.init();
});

/******/ })()
;
//# sourceMappingURL=general.js.map