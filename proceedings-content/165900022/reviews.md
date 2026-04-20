# Review 1

> Provide a link to an external repository with the MONA implementation of the proposed encodings.

**DONE** Future work. On fait rien.

> Improve clarity by reducing density and adding more explanations, intuition, and possibly running examples. In order to fit within the page limits of COORDINATION while adding explanations, consider moving some technical material out. For instance, the part on bounding cutwidth appears somewhat tangential to me and could be deferred to a future journal version, to leave more space for better exposition of the core contributions.

**DONE** Pas très précis comme suggestion. On fait rien.

## Detailed comments and typos

> In page 2 you say it can express 36 out of 42 models, while in the rest of the paper you say 39 out of 42. Which is true?

**DONE** Remplacé 36 par 39

> Invocation and return times can be absolute or relative: what do you mean with relative time? In the rest of the paper it seems to me they are basically global time (time on different entities are comparable).

**DONE** phrase pour éviter des confusions

> acurate -> accurate

**DONE**

> Isn't the assumption that no two operations can run concurrently on the same process in fact a consequence of the notion of process? How can it not be true?

**DONE** Ajouté idée appels récursifs, threads dans note de bas de page.

> You ask no Zeno behaviour for end times. This mostly cover also the Zeno behaviour for start times, but not for non-terminating operations: you may have infinitely many non-terminating operations before a finite time, without violating your condition (but I don't think you want this).

**DONE** corrigé comme suggéré, même si ce n'est pas nécessaire (c'est juste plus clair)

> Footnote 6: I don't think it is particularly meaningful to consider transfinite histories indexed by ordinals, as it cannot relate to any physically realisable system, I'd remove the footnote to leave space for more explanations.

**DONE** On laisse la footnote.

> It seems weird to me that there are no coherence requirements in general between the three relations, rb/ar/vis. I would expect in a physical system that vis \subseteq rb \subseteq ar (which in the end is what you later assume). Please discuss why you cannot assume this in general.

**DONE** Ajout de plein de remarques et discussions à la fin de la section 2.

> Remark 1: move the "also" from the first to the second paragraph.

**DONE** bof, si ça lui fait plaisir...

> In page 8, definition of b \in ctxt(a), you don't need the last clause a.type = read since you only apply ctxt to an a which already has that property from RValR. Please, add some explanation text for this long sequences of formulas.

**DONE** réécriture de cette partie

> I am not sure it makes sense to include formulas in the meta logic, that you don't explain the paper: they are not understandable unless you read [24], but at that point you can read then directly in [24]. Save space and improve clarity by leaving them out.

**DONE** réécriture de cette partie

> Each line \ell of a bitvector is associated with a second order variable: I don't understand what you mean by line of a bitvector, and also how you plan to use those extra free variables. Also, all the formulas translated seem to only look at the bitvectors for starts and not at those for ends, it is unclear why you need also the latter.

**DONE** c'est un lecteur qui n'est pas familier avec MONA. On ignore.

> "We write a->>b to mean.... with the current set E". It is not clear to me what is the current set E when the notation is used.

**DONE** j'ai enlevé la fin de la phrase.

> Your construction of the subgraph seems to me basically the "transitive reduction" of a directed acyclic graph, which has many known properties (being easy to compute, and unique, and generating the same transitive closure). I believe that in general your argument on the cutwidth could be made much shorter, just by noting that on each process the rb order is linear, so that in the transitive reduction each two links a->c and b->c need to have a and b on different processes. Given that you need to save space to improve readability, I would leave completely out this section on the bounded cutwidth. Maybe, you could just turn it into a remark explaining briefly and informally why it holds, and why is it relevant, without formal proofs.

**DONE** je sais pas ce qu'est la reduction transitive. Je pense qu'il faut garder la partie sur la cutwidth maintenant que le papier est accepté. Je suis pour ignorer complètement ce commentaire.

> translation to abstraction executions -> to abstract executions

**DONE**

> It feels weird to me to have the related works as a paragraph within the conclusions; I'd move it before the conclusions section.

**DONE** Voir TODO reviewer 2.
----------------------- REVIEW 2 ---------------------

> The abstract claims 39 expressible models while the body claims 36. Which is the correct number, and which three models (beyond those based on exact timing) account for the discrepancy?

**DONE** Etienne vérifier que c'est bien clair que les 3 modèles qui posent problème sont ceux avec contrainte quantitative de temps. Préciser aux endroits où on compte les modèles.

> Among the 39 (or 36) expressible models, how many remain decidable under the real-time and k-transient visibility restrictions? Is there a subset that is verifiable end-to-end with MONA without further assumptions?

**DONE** Etienne Première question: les deux restrictions suffisent à rendre la logique décidable, donc tous les modèles (toutes les formules) sont décidables dans ce cadre. Deuxième question: je la comprends comme: est-ce qu'il y a des modèles qui impliquent ces restrictions. À regarder... Real-time est un modèle, donc c'est k-transient qui pose question. FUTURE WORK

> The bound on cutwidth is 2m^2 where m is the number of processes. How does this translate in practice? For typical distributed systems benchmarks with, say, 3 to 10 processes, is the resulting tree decompositions width manageable for MONA?

**DONE** On en sait rien tant qu'on a pas expérimenté... et la cutwidth, c'est une indication théorique sur la complexité. Bonne question mais on dit déjà en conclusion que c'est un future work. A mon avis rien à faire. FUTURE WORK


> The conclusion mentions offline and centralised monitoring as a limitation. Have the authors considered whether the cutwidth bound could support a decomposed, process-local monitoring strategy, analogous to the bounded asynchrony results cited from prior work?


**DONE** Pareil, on dit déjà que c'est un FUTURE WORK


> Section 3.2: the formula for Finite(O) appears to contian a typo; the disjunction $\neg \exists a \in O \;\vee\; \exists a \in O.\; \forall b \in O.\; b.\mathit{stime} \leq a.\mathit{stime}$ reads oddly. The first disjunct seems to handle the empty set, but the logical structure should be clarified.

**DONE** Etienne ajouter des parenthèses

> Section 4.2, Remark 2: the simplifying assumption that no two operation extremities share a timestamp is reasonable, but a brief argument that this assumption does not change the MSO theory would be welcome.

**DONE** Etienne ajouter dans la section 2 qu'on fait cette hypothèse sur les historiques

> The related work discussion is placed inside the conclusion section. For a paper of this technical depth, a dedicated related work section would improve readability.

**DONE** Isabelle

> A few notation inconsistencies appear between the main text and the appendix (for example, the session order arrow sorr in the appendix is not introduced in the main text).

**TODO** Isabelle DONE?


> The use of reference [21] (Nipkow and Weber, 2020) alongside [19] (Klarlund and Moller, 2001) for MONA is slightly confusing; it would help to clarify it in the text which citation refers to the tool implementation and which to the user manual.

**DONE** Étienne 

----------------------- REVIEW 3 ---------------------

p4
> It is not necessary to assume that for any o, o.rtime = +\inf <=> o.oval = nabla

**TODO** Isabelle

p7.
- I don't see phi and nabla in the syntax of the logic, but they are used, e.g., when defining return value consistency.

**TODO** Isabelle

> It is mentioned that your logic is not able to capture timing constraints like TimedVisibility. I can understand that. However, the relation between k-bounded visibility and k-transient visibility is unclear. I wonder whether k-transient visibility implies something like TimedVisibility(k) (in Viotti & Vukolic’s presentation). That is, I wonder if the encoding of a HistMSO formula f results in something analogous to f \and TimedVisibility(k)

**DONE** Étienne La réponse est non. Quand on l'aura montré expérimentalement, ce sera bien. On ne va pas traiter juste cette question.

> p10. footnote 8. Comment a bit on this would be enlightening, otherwise the assumption seems to strong.

**DONE** footnote enlevée, ce point devient justifié par la def de ce qu'est un historique.

> p14(l6).  G= (V,->) shouldn't be  G= (H,->)?

**TODO** Isabelle

> p14. starting dates -> timestamps?

**TODO** Isabelle

p15. Missing a proof of algorithm correctness.

**TODO** Isabelle ajouter une phrase en fin de 5.2 pour expliquer que 5.3 aborde la question de la correction de l'algo.

p16. Proposition 1. I don't think all the p for traversing from a to b through h_i are all the same (this would imply they are all in the same session).

**TODO** Isabelle

p16. \gamma_p(L) and \lambda_p(L). There are some inconsistent use of L, O, R (just for clarity)
\lambda_p has not been defined.

**TODO** Isabelle


----------------------- REVIEW 4 ---------------------

> The semantics of the logic could appear in appendix, to ensure the paper is self-contained.

**TODO** Étienne ajouter en annexe

> Section 3.2 lacks illustrative examples.

**TODO** Étienne ajouter des exemples

> The algorithm for building a graph of finite history could be defined as a functional program (and proved correct).

**TODO** Isabelle