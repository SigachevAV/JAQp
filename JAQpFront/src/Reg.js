import './style/Reg.css';
import './style/Default.css';
import React from "react";
import { useNavigate } from "react-router-dom";
import { useForm } from "react-hook-form";
import RegisterUser from './http/userApi';
import Alert from '@mui/material/Alert';

export const types = {
    INFO: 'info',
    SUCCESS: 'success',
    ERROR: 'error'
  }

function Reg() {
    const { handleSubmit, register, getValues, formState: { errors } } = useForm();
    const onSubmit = values => {
        RegisterUser(values.email, values.password).then((res) => { if (res) gotoLoginPage(); else alert('Что-то пошло не так. Попробуйте позднее') });
    };
    const navigate = useNavigate();

    const gotoLoginPage = () => navigate("/");

    return (
        <div className="elongated_app" style={{ backgroundImage: 'url(img/background.svg)' }}>
            <form onSubmit={handleSubmit(onSubmit)} className='empty_box' >

                <div className='elongated_box'>
                    <p className='h1'>Регистрация</p>
                </div>
                <div className='elongated_box'>
                    <div>
                        <p className='bold_text' style={{ float: 'left' }}>1.Почта</p>

                    </div>
                    <div className='evenly_distributed_field'>
                        <img className='icon' src='img/Mail.svg'></img>
                        <input className='customInput' {...register("email", {
                            required: "* Обязательное поле",
                            pattern: {
                                value: /^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,}$/i,
                                message: "* Некорректный email"
                            }
                        })} />
                    </div>
                    <div className='error_text'>{errors.email && errors.email.message}</div>

                </div>
                <div className='elongated_box'>
                    <div>
                        <p className='bold_text' style={{ float: 'left' }}>2.Пароль</p>


                    </div>
                    <div className='evenly_distributed_field'>
                        <img className='icon' src='img/Password_Key.svg'></img>
                        <input className='customInput' type='password'
                            {...register("password", {
                                required: "* Обязательное поле",
                                pattern: {
                                    value: /^([a-zA-Z0-9!@#$%^&*]){8,20}$/,
                                    message: "* Некорректный пароль: 8-20 символов."
                                }
                            })}
                        />
                    </div>
                    <div className='error_text'>{errors.password && errors.password.message}</div>
                </div>
                <div className='elongated_box'>
                    <div>
                        <p className='bold_text' style={{ float: 'left' }}>3.Подтверждение пароля</p>

                    </div>
                    <div className='evenly_distributed_field'>
                    <img className='icon' src='img/Password_Key.svg'></img>
                        <input className='customInput' type='password'
                            {...register("repeat_password", {
                                required: "* Обязательное поле",
                                validate: (value) => {
                                    const { password } = getValues();
                                    return password === value || "* Пароли не совпадают";
                                }
                            })} style={{ marginLeft: 'auto' }} /></div>
                    <div className='error_text'>{errors.repeat_password && errors.repeat_password.message}</div>
                </div>
                <button className='button' type="submit">Зарегистрироваться</button>
            </form>

        </div>
    );
}

export default Reg;