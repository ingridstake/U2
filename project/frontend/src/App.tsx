import React from 'react';
import './app.css';
import AllEvents from './pages/allEvents'
import Home from './pages/home'
import Layout from './pages/layout'
import { BrowserRouter, Routes, Route } from "react-router-dom";

/**
 * Base of application.
 * @returns the application with all its components
 */

export default function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path='/' element={<Layout/>}>
          <Route index element={<Home/>}/>
          <Route path='home' element={<Home/>}/>
          <Route path="allEvents" element={<AllEvents/>}/>
        </Route>
      </Routes>
    </BrowserRouter>
  );
}