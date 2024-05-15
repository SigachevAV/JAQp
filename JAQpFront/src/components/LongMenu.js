import { useNavigate } from "react-router-dom";

import diceIcon from "../icons/dice.svg";
import searchIcon from "../icons/search.svg";
import constructorIcon from "../icons/constructor.svg";
import vkIcon from "../icons/vk.svg";
import telegramIcon from "../icons/telegram.svg";
import userEmptyIcon from "../icons/userEmpty.svg";

import "../css/longmenu.css";

function LongMenu() {
  /* navigate */
  const navigate = useNavigate();
  const gotoUserSettingsPage = () => navigate("/settingsId=?");
  const gotoConstructorPage = () => navigate("/constructor");
  const gotoLoginPage = () => navigate("/login");

  return (
    <div className="long_menu_header_container">
      <div className="long_menu_block">
        <div className="long_menu_evenly_distributed_block">
          <p className="logo_name">JAQp</p>
          <p className="logo_text">Just Another Quiz platform</p>
        </div>
        <div className="long_menu_evenly_distributed_block">
          <img
            alt=""
            className="button_icon"
            src={diceIcon}
            style={{ cursor: "pointer", paddingRight: "4px" }}
          ></img>
          <p className="text" style={{ cursor: "pointer" }}>
            Квизы
          </p>
          <p className="text" style={{ cursor: "pointer" }}>
            Лучшее
          </p>
          <img
            className="button_icon"
            alt=""
            src={searchIcon}
            style={{ cursor: "pointer" }}
          ></img>
          <div
            style={{ cursor: "pointer" }}
            className="user_block"
            onClick={
              localStorage.getItem("token") === null ||
              localStorage.getItem("token") === undefined
                ? gotoLoginPage
                : gotoConstructorPage
            }
          >
            <img
              src={constructorIcon}
              style={{ stroke: "black", fill: "black" }}
              className="icon"
              alt=""
            ></img>
            <p className="simple_text">Конструктор</p>
          </div>
        </div>
        <div className="long_menu_evenly_distributed_block">
          <img
            alt=""
            className="button_icon"
            src={telegramIcon}
            style={{ cursor: "pointer" }}
          ></img>
          <img
            alt=""
            className="button_icon"
            src={vkIcon}
            style={{ cursor: "pointer" }}
          ></img>
        </div>
        <div
          style={{ cursor: "pointer" }}
          className="user_block"
          onClick={
            localStorage.getItem("token") === null ||
            localStorage.getItem("token") === undefined
              ? gotoLoginPage
              : gotoUserSettingsPage
          }
        >
          <img src={userEmptyIcon} className="icon" alt=""></img>
          <p className="simple_text">
            {localStorage.getItem("userName")
              ? localStorage.getItem("userName")
              : "Войти"}
          </p>
        </div>
      </div>
    </div>
  );
}

export default LongMenu;