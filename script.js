const orders = [
  { id: 'TLR-1024', customer: 'Rohan Sharma', item: 'Suit', status: 'Stitching', delivery: 'Out for delivery' },
  { id: 'TLR-1008', customer: 'Priya Joshi', item: 'Kurta', status: 'Ironing', delivery: 'Packing' },
  { id: 'TLR-1031', customer: 'Amit Patel', item: 'Shirt', status: 'Packing', delivery: 'Ready for pickup' },
];

const customers = [
  { name: 'Rohan Sharma', email: 'rohan@example.com', phone: '9876543210', order: 'TLR-1024' },
  { name: 'Priya Joshi', email: 'priya@example.com', phone: '9123456780', order: 'TLR-1008' },
  { name: 'Amit Patel', email: 'amit@example.com', phone: '9988776655', order: 'TLR-1031' },
];

const delivery = [
  { order: 'TLR-1024', address: '14 Rosewood Lane, Mumbai', rider: 'Akash', status: 'Out for delivery' },
  { order: 'TLR-1008', address: '82 Green Avenue, Pune', rider: 'Riya', status: 'Packing' },
  { order: 'TLR-1031', address: '23 Lake View, Bangalore', rider: 'Meera', status: 'Ready for pickup' },
];

function initLoginPage() {
  const form = document.getElementById('loginForm');
  if (!form) return;

  form.addEventListener('submit', (event) => {
    event.preventDefault();
    const role = document.getElementById('role').value;
    const identity = document.getElementById('identity').value.trim();

    if (!identity) {
      alert('Please enter your email or mobile number.');
      return;
    }

    if (role === 'admin') {
      window.location.href = 'dashboard.html';
    } else {
      window.location.href = 'index.html';
    }
  });
}

function renderDashboard() {
  const ordersBody = document.getElementById('ordersBody');
  const customerList = document.getElementById('customerList');
  const deliveryGrid = document.getElementById('deliveryGrid');
  const metricOrders = document.getElementById('metricOrders');
  const metricOpen = document.getElementById('metricOpen');
  const metricDelivery = document.getElementById('metricDelivery');

  if (metricOrders) {
    metricOrders.textContent = orders.length;
  }

  if (metricOpen) {
    metricOpen.textContent = orders.filter((order) => order.status !== 'Packing').length;
  }

  if (metricDelivery) {
    metricDelivery.textContent = delivery.length;
  }

  if (ordersBody) {
    ordersBody.innerHTML = orders
      .map(
        (order) => `
        <tr>
          <td>${order.id}</td>
          <td>${order.customer}</td>
          <td>${order.item}</td>
          <td>${order.status}</td>
          <td><button class="button button-secondary" data-order="${order.id}">Next step</button></td>
        </tr>
      `
      )
      .join('');
  }

  if (customerList) {
    customerList.innerHTML = customers
      .map(
        (customer) => `
        <li class="customer-item">
          <strong>${customer.name}</strong>
          <span>${customer.email}</span>
          <span>${customer.phone}</span>
          <span>Order: ${customer.order}</span>
        </li>
      `
      )
      .join('');
  }

  if (deliveryGrid) {
    deliveryGrid.innerHTML = delivery
      .map(
        (item) => `
        <article class="delivery-card">
          <h3>${item.order}</h3>
          <p><strong>Address:</strong> ${item.address}</p>
          <p><strong>Rider:</strong> ${item.rider}</p>
          <p><strong>Status:</strong> ${item.status}</p>
        </article>
      `
      )
      .join('');
  }

  const actionButtons = document.querySelectorAll('button[data-order]');
  actionButtons.forEach((button) => {
    button.addEventListener('click', () => {
      const orderId = button.dataset.order;
      const order = orders.find((item) => item.id === orderId);

      if (!order) return;

      const statusOrder = ['Cutting', 'Stitching', 'Stitching Complete', 'Ironing', 'Packing'];
      const currentIndex = statusOrder.indexOf(order.status);
      const nextIndex = Math.min(currentIndex + 1, statusOrder.length - 1);
      order.status = statusOrder[nextIndex];

      if (order.status === 'Packing') {
        const deliveryRow = delivery.find((row) => row.order === order.id);
        if (deliveryRow) {
          deliveryRow.status = 'Ready for pickup';
        }
      }

      renderDashboard();
    });
  });
}

function initDashboardPage() {
  if (!document.getElementById('ordersBody')) return;
  renderDashboard();
}

window.addEventListener('DOMContentLoaded', () => {
  initLoginPage();
  initDashboardPage();
});
