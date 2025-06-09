//import { useState } from 'react'
import Main_Div from "./assets/components/Main_Div.tsx";
import Bottom_forme from './assets/components/Bottom_forme'
import { BrowserRouter, Routes, Route } from "react-router-dom";
import Login from "./assets/components/Login.tsx";
import './App.css'

function App() {

  return (

       <>
           <BrowserRouter>
               <Routes>
                   <Route path="/" element={<Login />} />
                   <Route path="/home"
                       element={<>
                           <Main_Div />
                           <Bottom_forme />
                       </>
                   }/>

               </Routes>
           </BrowserRouter>


       </>
  );
}

export default App
