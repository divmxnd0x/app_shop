<template>
  <div class="h-screen">
    <ClientOnly>
      <SiteHeader/>
    </ClientOnly>
    <slot class="mb-auto"/>
    <SiteFoot/>
  </div>
</template>