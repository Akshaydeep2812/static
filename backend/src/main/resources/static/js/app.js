document.addEventListener('DOMContentLoaded', () => {
  const buttons = document.querySelectorAll('.advance-order');
  buttons.forEach((button) => {
    button.addEventListener('click', async () => {
      const orderId = button.dataset.order;
      if (!orderId) return;

      const response = await fetch(`/api/orders/${orderId}/advance`, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
      });

      if (response.ok) {
        window.location.reload();
      } else {
        const error = await response.json();
        alert(error.message || 'Unable to advance order');
      }
    });
  });
});
