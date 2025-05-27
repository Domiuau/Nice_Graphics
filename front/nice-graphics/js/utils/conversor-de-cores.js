function shadeColor(color, percent) {
    const num = parseInt(color.slice(1), 16);
    const r = (num >> 16) & 0xFF;
    const g = (num >> 8) & 0xFF;
    const b = num & 0xFF;
    
    const newR = Math.min(255, Math.max(0, r + Math.round((percent / 100) * 255)));
    const newG = Math.min(255, Math.max(0, g + Math.round((percent / 100) * 255)));
    const newB = Math.min(255, Math.max(0, b + Math.round((percent / 100) * 255)));
    
    return "#" + ((1 << 24) + (newR << 16) + (newG << 8) + newB)
      .toString(16)
      .slice(1);
  }
  
  function getBrightness(color) {
    const num = parseInt(color.slice(1), 16);
    const r = (num >> 16) & 0xFF;
    const g = (num >> 8) & 0xFF;
    const b = num & 0xFF;
    
    return (r * 299 + g * 587 + b * 114) / 1000;
  }
  
  function adjustColor(color, percent) {
    const brightness = getBrightness(color);
    if (brightness > 128) {
      return shadeColor(color, -Math.abs(percent));
    } else {
      return shadeColor(color, Math.abs(percent));
    }
  }
  
  export function highlightColors(colors, percent) {
    return colors.map(color => adjustColor(color, percent));
  }