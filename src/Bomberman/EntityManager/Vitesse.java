package Bomberman.EntityManager;

public enum Vitesse {
        LENT(1500),MOYEN(750),RAPIDE(500),TRES_RAPIDE(350),ONLY_PROJECTILE(250);

        private int durationMs;

        Vitesse(int durationMs) {
            this.durationMs = durationMs;
        }

        public int getDurationMs() {
            return durationMs;
        }

}
