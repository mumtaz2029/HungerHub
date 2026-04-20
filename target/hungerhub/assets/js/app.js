const fallbackFavorites = [
    {
        name: "Firecracker Paneer Pizza",
        category: "Pizza",
        description: "Crispy crust, smoky paneer, bell peppers, mozzarella, and a bright chilli drizzle.",
        image: "https://images.unsplash.com/photo-1604382354936-07c5d9983bd3?auto=format&fit=crop&w=900&q=80",
        rating: "4.9",
        price: 349,
        accent: "#ef4444"
    },
    {
        name: "Royal Hyderabadi Biryani",
        category: "Biryani",
        description: "Layered basmati rice, aromatic spices, fried onions, mint, and cooling raita.",
        image: "https://images.unsplash.com/photo-1633945274405-b6c8069047b0?auto=format&fit=crop&w=900&q=80",
        rating: "4.8",
        price: 289,
        accent: "#f97316"
    },
    {
        name: "Stacked Street Burger",
        category: "Burger",
        description: "Toasted bun, juicy patty, cheese, crunchy greens, pickles, and signature hub sauce.",
        image: "https://images.unsplash.com/photo-1568901346375-23c9450c58cd?auto=format&fit=crop&w=900&q=80",
        rating: "4.7",
        price: 229,
        accent: "#22c55e"
    },
    {
        name: "Choco Lava Cloud",
        category: "Dessert",
        description: "Warm chocolate cake with a molten center, vanilla cream, and cocoa crumble.",
        image: "https://images.unsplash.com/photo-1606313564200-e75d5e30476c?auto=format&fit=crop&w=900&q=80",
        rating: "4.9",
        price: 179,
        accent: "#a855f7"
    }
];

const grid = document.querySelector("#favoritesGrid");

async function loadFavorites() {
    try {
        const response = await fetch("api/menu", { headers: { Accept: "application/json" } });
        if (!response.ok) {
            throw new Error(`Menu API returned ${response.status}`);
        }
        renderFavorites(await response.json());
    } catch (error) {
        renderFavorites(fallbackFavorites);
    }
}

function renderFavorites(items) {
    grid.innerHTML = items.map((item) => `
        <article class="favorite-card">
            <img src="${item.image}" alt="${item.name}">
            <div class="favorite-body">
                <div class="dish-meta">
                    <span>${item.category}</span>
                    <span>★ ${item.rating}</span>
                </div>
                <h3>${item.name}</h3>
                <p>${item.description}</p>
                <div class="price-row">
                    <strong>₹${item.price}</strong>
                    <button class="add-button" style="background:${item.accent}" aria-label="Add ${item.name} to cart">+</button>
                </div>
            </div>
        </article>
    `).join("");
}

loadFavorites();
