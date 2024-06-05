import './style/App.css';
import './style/Default.css';
import React, {
  useState
} from "react";
import { useNavigate } from "react-router-dom";
import LoginUser from './http/userApi';

function App() {

  const navigate = useNavigate();

  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  const handleSubmit = (e) => {
    e.preventDefault();
    console.log({ email, password });
    LoginUser(email, password);
  };

  const gotoRegPage = () => navigate("/registration");
  const gotoMainPage = () => navigate("/main");
  return (
    <div className="centered_app" style={{ backgroundImage: 'url(img/background.svg)' }}>

      <div className='compressed_box' >
        <div>
          <p className='h1'>JAQp</p>
          <p className='h2'>Авторизация</p>
        </div>
        <div className='compressed_box'>
          <div className='evenly_distributed_field '>
            <img className='icon' src='img/Male_User.svg'></img>
            <input className='customInput' type='email'
              name='email'
              id='email'
              value={email}
              required
              onChange={(e) => setEmail(e.target.value)}></input>
          </div>
          <div className='evenly_distributed_field '>
            <img className='icon' src='img/Password_Key.svg'></img>
            <input className='customInput' type='password'
                            name='password'
                            id='password'
                            required
                            value={password}
                            onChange={(e) => setPassword(e.target.value)}></input>
          </div>
          <div className='evenly_distributed_field '>

            <button className='textButton' onClick={gotoRegPage} >Регистрация</button>
            <button className='textButton'>Забыли пароль?</button>
          </div>
        </div>
        <button className='center_primary_button' onClick={handleSubmit}>Войти</button>
      </div>
    </div>
  );
}

export default App;


