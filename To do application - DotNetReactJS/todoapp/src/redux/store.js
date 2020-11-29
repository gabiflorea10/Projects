import { configureStore } from '@reduxjs/toolkit';
import TodosReducer from './todosReducer';

export default configureStore({
  reducer: {
    todosReducer: TodosReducer,
  },
});
