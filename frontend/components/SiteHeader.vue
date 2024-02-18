<script setup>
const router = useRouter()
const utils = useUtils()

const items = [utils.getAvailableCategories().map(cat => {
  return {
    label: cat.displayName,
    click: () => router.push(`/products?category=${cat.name}`)
  }
})]
</script>

<template>
  <div class="flex gap-3 mb-12">
    <UButton class="py-3 text-2xl" to="/products">Produkty</UButton>
    <UButton class="py-3 text-2xl" to="/search">Wyszukiwanie</UButton>
    <UDropdown :popper="{arrow: true}" :items="items">
      <UButton class="py-3 text-2xl">Kategorie</UButton>
    </UDropdown>
  </div>
</template>
