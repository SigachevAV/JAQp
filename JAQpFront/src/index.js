import React from "react";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import { createBrowserHistory } from "history";
import ReactDOM from "react-dom/client";
import "./index.css";
import Auth from "./pages/Auth";
import Reg from "./pages/Reg";
import Main from "./pages/Main";
import QuizMain from "./pages/QuizMain";
import QuizAnswers from "./pages/QuizAnswers";
import Settings from "./pages/Settings";
import Constructor from "./pages/Constructor";
import reportWebVitals from "./reportWebVitals";
import "./fonts/FuturaPT-Medium.ttf";
import QuizResult from "./pages/QuizResult";
export const history = createBrowserHistory();

const root = ReactDOM.createRoot(document.getElementById("root"));
root.render(
  <BrowserRouter history={history}>
    <Routes>
      <Route exact path="/login" element={<Auth />} />
      <Route exact path="/registration" element={<Reg />} />
      <Route exact path="/main" element={<Main />} />
      <Route exact path="/constructor" element={<Constructor />} />
      <Route exact path="/settingsId=?" element={<Settings />} />
      <Route exact path="/quiz/:id/result" element={<QuizResult />} />
      <Route exact path="/quiz/:id/question/:idquestion" element={<QuizAnswers />} />
      <Route exact path="/quiz/:id" element={<QuizMain />} />
    </Routes>
  </BrowserRouter>
);

reportWebVitals();
