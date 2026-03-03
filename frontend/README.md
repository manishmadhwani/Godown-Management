# Godown Management – Frontend

This is the React app for Godown Management. You register, login, add godowns, and see your list here.

---

## How to run it

You need to be inside this **frontend** folder first. Open terminal (or Command Prompt) and type:

```
cd frontend
```

First time only – install the packages:

```
npm install
```

If that gives an error about scripts being disabled, try:

```
npm install --ignore-scripts
```

Then start the app:

```
npm run dev
```

When it’s ready you’ll see something like “Local: http://localhost:5173/” in the terminal. Open your browser and go to **http://localhost:5173**. That’s it. Keep the terminal open while you’re using the app.

One more thing – the backend has to be running on port 8087. If it’s not, the app will show “Cannot connect to backend”. So start the backend first, then run the frontend.

---

## What’s in the app

- **Register** – create account (name, password, contact, address)
- **Login** – sign in with contact number and password
- **Dashboard** – add a godown (name, address, city/state from dropdown), see “My Godowns” list
- **Add items** – from the dashboard you can add items to a godown (comodity, marka, packing, address from)

Cities in the dropdown come from the backend. The app talks to the backend at http://localhost:8087/api/godownManagement (Swagger is there too if you need it).

---

## Other commands

- `npm run build` – build for production
- `npm run preview` – preview the production build

