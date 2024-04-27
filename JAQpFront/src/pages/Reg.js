import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import { useForm } from "react-hook-form";

import { RegisterUser } from "../http/userApi";

import Menu from "../components/Menu";
import MessageAlert from "../components/alerts/MessageAlert";

import maleIcon from "../icons/male.svg";
import passwordIcon from "../icons/password.svg";

import "../css/reg.css";
import "../css/font.css";

function Reg() {
  /* navigate */
  const navigate = useNavigate();
  const gotoLoginPage = () => navigate("/login");

  /* setters */
  const [showAlert, setShowAlert] = useState(false);

  /* visual */
  const handleShowAlert = () => {
    setShowAlert(true);
  };

  /* form */
  const {
    handleSubmit,
    register,
    getValues,
    formState: { errors },
  } = useForm();

  const onSubmit = (values) => {
    RegisterUser(values.email, values.password).then((res) => {
      if (res) gotoLoginPage();
      else handleShowAlert();
    });
  };

  return (
    <div
      className="reg_window"
      style={{ backgroundImage: "url(img/background.svg)" }}
    >
      <div>
        <Menu />
      </div>
      <div className="reg_workspace">
        <form onSubmit={handleSubmit(onSubmit)} className="reg_whitecard">
          {showAlert && (
            <MessageAlert
              variant="danger"
              message="Что-то пошло не так. Попробуйте позднее"
            />
          )}
          <div>
            <p className="h1">JAQp</p>
            <p className="h2">Регистрация</p>
          </div>
          <div>
            <div className="reg_evenly_distributed_field">
              <img alt="" className="reg_icon" src={maleIcon}></img>
              <input
                placeholder="Логин"
                className="reg_input"
                {...register("email", {
                  required: "* Обязательное поле",
                })}
              />
            </div>
            <div className="error_text reg_error_text_position">
              {errors.email && errors.email.message}
            </div>
          </div>
          <div>
            <div className="reg_evenly_distributed_field">
              <img alt="" className="reg_icon" src={passwordIcon}></img>
              <input
                placeholder="Пароль"
                className="reg_input"
                type="password"
                {...register("password", {
                  required: "* Обязательное поле",
                  pattern: {
                    value: /^([a-zA-Z0-9!@#$%^&*]){4,20}$/,
                    message: "* Некорректный пароль: 4-20 символов.",
                  },
                })}
              />
            </div>
            <div className="error_text reg_error_text_position">
              {errors.password && errors.password.message}
            </div>
          </div>
          <div>
            <div className="reg_evenly_distributed_field">
              <img alt="" className="reg_icon" src={passwordIcon}></img>
              <input
                placeholder="Подтверждение пароля"
                className="reg_input"
                type="password"
                {...register("repeat_password", {
                  required: "* Обязательное поле",
                  validate: (value) => {
                    const { password } = getValues();
                    return password === value || "* Пароли не совпадают";
                  },
                })}
                style={{ marginLeft: "auto" }}
              />
            </div>
            <div className="error_text reg_error_text_position">
              {errors.repeat_password && errors.repeat_password.message}
            </div>
          </div>
          <div className="reg_evenly_distributed_field">
            <button className="reg_textbutton" onClick={gotoLoginPage}>
              Авторизация
            </button>
            <button className="reg_button" type="submit">
              Зарегистрироваться
            </button>
          </div>
        </form>
      </div>
    </div>
  );
}

export default Reg;